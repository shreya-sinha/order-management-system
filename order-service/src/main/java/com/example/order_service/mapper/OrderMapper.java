package com.example.order_service.mapper;

import com.example.order_service.dto.OrderDto;
import com.example.order_service.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper to map Order dto to entity and vice-versa
 */
@Component
public class OrderMapper {

    public OrderDto toDto(OrderEntity saved) {
        OrderDto dto = new OrderDto();
        dto.setId(saved.getId());
        dto.setCustomerName(saved.getCustomerName());
        dto.setShippingAddress(saved.getShippingAddress());
        dto.setOrderDate(saved.getOrderDate());
        dto.setTotalCost(saved.getTotalCost());
        return dto;
    }

    public List<OrderDto> toDto(List<OrderEntity> entity) {
        List<OrderDto> dtos = new ArrayList<>();
        entity.forEach(e -> dtos.add(toDto(e)));
        return dtos;
    }

    public OrderEntity toEntity(OrderDto order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setCustomerName(order.getCustomerName());
        entity.setShippingAddress(order.getShippingAddress());
        entity.setTotalCost(order.getTotalCost());
        return entity;
    }

    public List<OrderEntity> toEntity(List<OrderDto> dtos) {
        List<OrderEntity> entities = new ArrayList<>();
        dtos.forEach(e -> entities.add(toEntity(e)));
        return entities;
    }
}
