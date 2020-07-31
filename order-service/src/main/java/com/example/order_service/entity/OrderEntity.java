package com.example.order_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEntity {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private String customerName;
    @Column
    @CreationTimestamp
    private Date orderDate;
    @Column
    private String shippingAddress;
    @Column
    private String totalCost;
}
