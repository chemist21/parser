package ru.mts.parser.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mts.parser.model.Item;
import ru.mts.parser.service.ItemService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class WildberriesServiceImpl implements WildberriesService {

    private final String Wildberries_URL = "https://catalog.wb.ru/brands/m/catalog?appType=1&curr=rub&dest=-1257786&limit=300";

    private final ItemService itemService;

    @Autowired
    public WildberriesServiceImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public List<Item> saveToDb(String brand) {
        String url = prepareUrl(brand);
        HttpResponse<String> response = sendRequest(url);
        List<Item> items = mapToItem(response);
        itemService.save(items);
        return items;
    }

    private String prepareUrl(String brand) {
        StringBuilder url = new StringBuilder(Wildberries_URL);
        url.append("&brand=").append(brand);
        return url.toString();
    }

    private HttpResponse<String> sendRequest(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .GET()
                .header("Accept", "application/json")
                .uri(URI.create(url))
                .build();
        try {
            return client.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Item> mapToItem(HttpResponse<String> response) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(String.valueOf(response.body()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode products = jsonNode.findValue("products");

        List<Item> resultListItems = null;
        try {
            if (!products.isNull()) {
                resultListItems = Arrays.asList(objectMapper.readValue(products.toString(), Item[].class));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return resultListItems;
    }
}
