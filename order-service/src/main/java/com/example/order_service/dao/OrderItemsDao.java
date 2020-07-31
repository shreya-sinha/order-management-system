package com.example.order_service.dao;

import com.example.order_service.entity.OrderItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsDao extends CrudRepository<OrderItems, Integer> {
    List<OrderItems> findByOrderId(Integer id);

    void deleteByOrderId(Integer orderId);
}
