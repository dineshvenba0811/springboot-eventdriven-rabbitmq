package com.dinesh.springboot.rabbitmq.orderservice.controller;

import com.dinesh.springboot.rabbitmq.orderservice.dto.Order;
import com.dinesh.springboot.rabbitmq.orderservice.dto.OrderEvent;
import com.dinesh.springboot.rabbitmq.orderservice.publish.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    private OrderProducer orderProducer;

    @PostMapping("/createOrders")
    public String createOrder(@RequestBody  Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent =new OrderEvent();
        orderEvent.setOrder(order);
        orderEvent.setOrderStatus("Pending");
        orderEvent.setOrderMessage("Order Created Successfully");
        orderProducer.sendMessages(orderEvent);
        return " order sent to rabbitmq";

    }
}
