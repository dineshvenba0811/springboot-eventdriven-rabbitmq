package com.dinesh.springboot.rabbitmq.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private  String orderName;
    private int orderQuantity;
    private double orderPrice;

}
