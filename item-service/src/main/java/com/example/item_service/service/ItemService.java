package com.example.item_service.service;

import com.example.item_service.dao.ItemDao;
import com.example.item_service.entity.Item;
import com.example.item_service.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemDao itemDao;

    public void save(final Item item) {
        itemDao.save(item);
    }

    public List<Item> getAll() {
        final List<Item> items = new ArrayList<>();
        itemDao.findAll().forEach(item -> items.add(item));
        return items;
    }

    public Item get(Integer productCode) throws ItemNotFoundException {
        Optional<Item> item = itemDao.findById(productCode);
        if (item.isPresent()) {
            return item.get();
        }
        throw new ItemNotFoundException("Unidentified Item " + productCode);
    }

    public Item update(Item item) throws ItemNotFoundException {
        Item existing = get(item.getProductCode());
        if (item.getProductName() != null) {
            existing.setProductName(item.getProductName());
        }
        if (item.getQuantity() != null) {
            existing.setQuantity(item.getQuantity());
        }
        return itemDao.save(existing);
    }

    public Integer delete(Integer productCode) throws ItemNotFoundException {
        if (itemDao.existsById(productCode)) {
            itemDao.deleteById(productCode);
            return productCode;
        }
        throw new ItemNotFoundException("Unidentified Item " + productCode);
    }

    public Collection<Item> getAll(List<Integer> productCodes) throws ItemNotFoundException {
        if (productCodes == null || productCodes.isEmpty()) {
            return getAll();
        }

        Map<Integer, Item> itemFound = new HashMap<>();
        itemDao.findAllById(productCodes).forEach(
                foundProduct -> itemFound.put(foundProduct.getProductCode(), foundProduct));
        if (itemFound.size() < productCodes.size()) {
            List<Integer> missing = productCodes.stream()
                    .filter(code -> ! itemFound.containsKey(code)).collect(Collectors.toList());
            throw new ItemNotFoundException("Could not find products: "+missing.toString()+". Please remove these and try again");
        }
        return itemFound.values();
    }
}
