package ru.mts.parser.api;

import ru.mts.parser.model.Item;

import java.util.List;

public interface WildberriesService {
    List<Item> saveToDb(String brand);

}
