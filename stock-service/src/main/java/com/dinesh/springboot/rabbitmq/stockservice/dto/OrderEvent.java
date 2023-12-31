package com.dinesh.springboot.rabbitmq.stockservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderEvent {
    private  String orderStatus;
    private String orderMessage;
    private Order order;
}
