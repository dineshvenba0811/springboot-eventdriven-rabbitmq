package com.dinesh.springboot.rabbitmq.orderservice.publish;

import com.dinesh.springboot.rabbitmq.orderservice.dto.OrderEvent;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

@Service
public class OrderProducer {
    private Logger LOGGER= LoggerFactory.getLogger(OrderProducer.class);

    @Value("${order-service.queue.exchange}")
    private String exchangeName;

    @Value("${order-service.queue.routing-key}")
    private String orderRoutingKey;

    @Value("${email-service.queue.routing-key}")
    private String emailRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public  void sendMessages(OrderEvent orderEvent){
        LOGGER.info(String.format("message assigned to rabbitmq => %s",orderEvent.toString()));
        // order queue
        rabbitTemplate.convertAndSend(exchangeName,orderRoutingKey,orderEvent);
        //  email queue
        rabbitTemplate.convertAndSend(exchangeName,emailRoutingKey,orderEvent);
    }

}
