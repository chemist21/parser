package ru.mts.parser.service;

import org.springframework.stereotype.Service;
import ru.mts.parser.model.Item;

import java.util.List;

@Service
public interface ItemService {
    public void save(List<Item> items);

    public Item getById(Long id);

    public List<Item> getAllItems();
}
