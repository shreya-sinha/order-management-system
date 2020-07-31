package com.example.order_service.dao;

import com.example.order_service.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao  extends CrudRepository<OrderEntity, Integer> {
}
