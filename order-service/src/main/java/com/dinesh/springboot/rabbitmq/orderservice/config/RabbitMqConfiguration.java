package com.dinesh.springboot.rabbitmq.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${order-service.queue.name}")
    private String orderQueueName;

    @Value("${email-service.queue.name}")
    private String emailQueueName;

    @Value("${order-service.queue.exchange}")
    private String exchangeName;

    @Value("${order-service.queue.routing-key}")
    private String orderRoutingKey;

    @Value("${email-service.queue.routing-key}")
    private String emailRoutingKey;

    @Bean
    public Queue orderQueue(){
        return  new Queue(orderQueueName);
    }

    @Bean
    public Queue emailQueue(){
        return  new Queue(emailQueueName);
    }

    @Bean
    public TopicExchange exchangeName(){
        return  new TopicExchange(exchangeName);
    }

    @Bean
    public Binding bindingExchangeQueue(){
        return BindingBuilder.bind(orderQueue()).to(exchangeName()).with(orderRoutingKey);
    }

    @Bean
    public Binding bindingEmailExchangeQueue(){
        return BindingBuilder.bind(emailQueue()).to(exchangeName()).with(emailRoutingKey);
    }

    @Bean
    public MessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
