package com.example.item_service.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue
    private Integer productCode;
    @Column
    private String productName;
    @Column
    private Integer quantity;
}
