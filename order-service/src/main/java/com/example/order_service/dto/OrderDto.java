package com.example.order_service.dto;

import com.example.order_service.entity.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private int id;
    @NotBlank(message = "customerName cannot be empty")
    private String customerName;
    private Date orderDate;
    @NotBlank(message = "shippingAddress cannot be empty")
    private String shippingAddress;
    @NotBlank(message = "totalCost cannot be empty")
    private String totalCost;
    @NotEmpty(message = "Please specify field 'orderItemIds', eg. value: [1,2,3]")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Integer> orderItemIds;

    private List<Item> orderItems;
}
