package com.dinesh.springboot.rabbitmq.stockservice.consumer;

import com.dinesh.springboot.rabbitmq.stockservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private Logger LOGGER= LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = "${order-service.queue.name}")
    public void consumeOrder(OrderEvent orderEvent){
        LOGGER.info(String.format("order event recived =>%s",orderEvent.toString()));
    }
}
