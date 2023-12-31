package com.dinesh.springboot.rabbitmq.emailservice.consumer;

import com.dinesh.springboot.rabbitmq.emailservice.dto.OrderEvent;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {
    private Logger LOGGER= LoggerFactory.getLogger(EmailConsumer.class);

    @RabbitListener(queues = "${email-service.queue.name}")
    public void consumerOrderEmail(OrderEvent orderEvent){
        LOGGER.info(String.format("order received at Email consumer=> %s",orderEvent.toString()));
    }

}
