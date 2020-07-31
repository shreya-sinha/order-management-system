package com.example.item_service.controller;


import com.example.item_service.dto.MessageResponse;
import com.example.item_service.entity.Item;
import com.example.item_service.exception.ItemNotFoundException;
import com.example.item_service.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ItemService service;


    @PostMapping
    public ResponseEntity<MessageResponse> save(final @RequestBody @Valid Item item) {
        log.info("Create item");
        service.save(item);
        return ResponseEntity.ok(new MessageResponse("Stored item " + item.getProductCode()));
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<Item> get(final @PathVariable("productCode") Integer productCode)
            throws ItemNotFoundException {
        log.info("Get all items");
        return ResponseEntity.ok(service.get(productCode));
    }

    @DeleteMapping("/{productCode}")
    public ResponseEntity<MessageResponse> delete(final @PathVariable("productCode") Integer productCode)
            throws ItemNotFoundException {
        log.info("Delete item");
        return ResponseEntity.ok(new MessageResponse("Deleted item " + service.delete(productCode)));
    }

    @PutMapping("/{productCode}")
    public ResponseEntity<Item> update(final @RequestBody @Valid Item item,
                                       final @PathVariable("productCode") Integer productCode)
            throws ItemNotFoundException {
        log.info("Update item");
        item.setProductCode(productCode);
        return ResponseEntity.ok(service.update(item));
    }

    @GetMapping
    public ResponseEntity<Collection<Item>> get(final @RequestParam(value = "productCode", required = false) List<Integer> productCodes) throws ItemNotFoundException {
        log.info("Search items");
        return ResponseEntity.ok(service.getAll(productCodes));
    }
}
