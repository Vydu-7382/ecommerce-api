package com.example.ecommerce.controller;

import com.example.ecommerce.model.Item;
import com.example.ecommerce.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:5173/") // Allow frontend calls
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@Valid @RequestBody Item item) {
        return ResponseEntity.ok(service.addItem(item));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id) {
        Item item = service.getItemById(id);
        if (item == null) {
            return ResponseEntity.status(404).body("Item not found");
        }
        return ResponseEntity.ok(item);
    }
}

