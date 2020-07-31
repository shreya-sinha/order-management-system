package com.example.order_service.controller;

import com.example.order_service.dto.MessageResponse;
import com.example.order_service.dto.OrderDto;
import com.example.order_service.exception.ItemNotFoundException;
import com.example.order_service.exception.OrderCreationException;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderDto> save(final @RequestBody @Valid OrderDto order) throws ItemNotFoundException, OrderCreationException {
        log.info("Create order");
        Optional<OrderDto> saved = service.save(order);
        if (saved.isPresent()) {
            return ResponseEntity.ok(saved.get());
        }
        throw new OrderCreationException("Could not place order. Please try after some time.");
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        log.info("Get all orders");
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderDto> get(@PathVariable("orderId") @Valid Integer orderId) throws OrderNotFoundException {
        log.info("Get order {}", orderId);
        return ResponseEntity.ok(service.get(orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<MessageResponse> delete(final @PathVariable("orderId") Integer orderId)
            throws OrderNotFoundException {
        log.info("Delete order");
        return ResponseEntity.ok(new MessageResponse("Deleted order " + service.delete(orderId)));
    }
    /*@PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> update(final @RequestBody @Valid OrderEntity order,
                                       final @PathVariable("orderId") Integer orderId)
            throws OrderNotFoundException {
        log.info("Update order");
        order.setId(orderId);
        return ResponseEntity.ok(service.update(order));
    }*/
}
