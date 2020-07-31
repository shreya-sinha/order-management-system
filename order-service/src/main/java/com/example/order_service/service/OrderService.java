package com.example.order_service.service;

import com.example.order_service.client.ItemClient;
import com.example.order_service.dao.OrderDao;
import com.example.order_service.dao.OrderItemsDao;
import com.example.order_service.dto.OrderDto;
import com.example.order_service.entity.Item;
import com.example.order_service.entity.OrderEntity;
import com.example.order_service.entity.OrderItems;
import com.example.order_service.exception.ItemNotFoundException;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final OrderDao orderDao;
    private final OrderItemsDao orderItemsDao;
    private final ItemClient itemClient;
    private final OrderMapper orderMapper;

    public Optional<OrderDto> save(final OrderDto order) throws ItemNotFoundException {

        OrderEntity orderEntity = orderMapper.toEntity(order);
        OrderEntity saved = orderDao.save(orderEntity);
        OrderDto dto = orderMapper.toDto(saved);
        Optional<List<Item>> items = getItems(order.getOrderItemIds());
        if (items.isPresent()) {
            items.get().stream().forEach(i -> {
                OrderItems orderItems = new OrderItems();
                orderItems.setItemId(i.getProductCode());
                orderItems.setOrderId(saved.getId());
                orderItemsDao.save(orderItems);
            });
            dto.setOrderItems(items.get());
        }
        return Optional.of(dto);
    }

    public List<OrderDto> getAll() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderDao.findAll().forEach(order -> {
            OrderDto orderDto = orderMapper.toDto(order);
            orderDtoList.add(orderDto);

            List<OrderItems> orderItems = orderItemsDao.findByOrderId(order.getId());
            List<Integer> itemIds = new ArrayList<>();
            orderItems.stream().forEach(orderItem -> itemIds.add(orderItem.getItemId()));
            Optional<List<Item>> itemsOp = getItems(itemIds);

            if (itemsOp.isPresent()) {
                orderDto.setOrderItems(itemsOp.get());
            }
        } );
        return orderDtoList;
    }

    public OrderDto get(Integer productCode) throws OrderNotFoundException {
        Optional<OrderEntity> orderOp = orderDao.findById(productCode);
        if (orderOp.isPresent()) {
            OrderEntity order = orderOp.get();
            OrderDto orderDto = orderMapper.toDto(order);
            List<OrderItems> orderItems = orderItemsDao.findByOrderId(order.getId());
            List<Integer> itemIds = new ArrayList<>();
            orderItems.stream().forEach(orderItem -> itemIds.add(orderItem.getItemId()));
            Optional<List<Item>> itemsOp = getItems(itemIds);
            if (itemsOp.isPresent()) {
                orderDto.setOrderItems(itemsOp.get());
            }
            return orderDto;
        }
        throw new OrderNotFoundException("Missing Order " + productCode);
    }

    public Integer delete(Integer orderId) throws OrderNotFoundException {
        if (orderDao.existsById(orderId)) {
            orderDao.deleteById(orderId);
            orderItemsDao.deleteByOrderId(orderId);
            return orderId;
        }
        throw new OrderNotFoundException("Missing Order " + orderId);
    }

    private Optional<List<Item>> getItems (List<Integer> orderItemIds) {
        ResponseEntity<Collection<Item>> itemResponse = itemClient.get(orderItemIds);
        if (itemResponse.getStatusCode() != null && itemResponse.getStatusCode().is2xxSuccessful()) {
            List<Item> items = new LinkedList<>();
            items.addAll(itemResponse.getBody());
            return Optional.of(items);
        }
        return Optional.empty();
    }
}
