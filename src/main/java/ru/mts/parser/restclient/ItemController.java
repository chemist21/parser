package ru.mts.parser.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.parser.api.WildberriesService;
import ru.mts.parser.model.Item;
import ru.mts.parser.service.ItemService;

import java.util.List;

@RestController
@Slf4j
public class ItemController {

    private final WildberriesService wildberriesService;
    private final ItemService itemService;

    public ItemController(WildberriesService wildberriesService, ItemService itemService) {
        this.wildberriesService = wildberriesService;
        this.itemService = itemService;
    }

    @RequestMapping(value = "/wildberries/{brand_id}", method = RequestMethod.GET)
    public List<Item> saveItemsToDB(@PathVariable("brand_id") String brand_id) {
        log.info("save to db items by brand_id {}", brand_id);
        return wildberriesService.saveToDb(brand_id);
    }

    @RequestMapping(value = "/wildberries/item", method = RequestMethod.GET)
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @RequestMapping(value = "/wildberries/item/{id}", method = RequestMethod.GET)
    public Item getItemsById(@PathVariable("id") Long id) {
        return itemService.getById(id);
    }
}
