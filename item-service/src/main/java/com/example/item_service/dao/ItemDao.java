package com.example.item_service.dao;

import com.example.item_service.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao  extends CrudRepository<Item, Integer> {
}
