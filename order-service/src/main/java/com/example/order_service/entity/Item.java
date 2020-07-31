package com.example.order_service.entity;

import lombok.Data;

@Data
public class Item {

    private Integer productCode;
    private String productName;
    private Integer quantity;
}
