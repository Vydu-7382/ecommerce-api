package com.example.ecommerce.service;

import com.example.ecommerce.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final List<Item> items = new ArrayList<>();
    private long idCounter = 1;

    public Item addItem(Item item) {
        item.setId(idCounter++);
        items.add(item);
        return item;
    }

    public Item getItemById(Long id) {
        return items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}