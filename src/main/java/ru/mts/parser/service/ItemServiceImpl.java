package ru.mts.parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.parser.model.Item;
import ru.mts.parser.repository.ItemsRepository;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemServiceImpl(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public void save(List<Item> items) {
        items.stream()
                .forEach(item -> itemsRepository.save(item));
    }

    @Override
    public Item getById(Long id) {
        return itemsRepository.findById(id).get();
    }

    @Override
    public List<Item> getAllItems() {
        return itemsRepository.findAll();
    }
}
