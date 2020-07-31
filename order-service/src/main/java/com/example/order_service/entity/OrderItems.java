package com.example.order_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Data
@Entity
public class OrderItems {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer orderId;
    private Integer itemId;
}
