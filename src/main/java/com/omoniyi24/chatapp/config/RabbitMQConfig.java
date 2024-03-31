package com.omoniyi24.chatapp.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;


@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String SPRING_RABBITMQ_HOST;

    @Value("${spring.rabbitmq.port}")
    private int SPRING_RABBITMQ_PORT;

    @Value("${spring.rabbitmq.username}")
    private String SPRING_RABBITMQ_USERNAME;

    @Value("${spring.rabbitmq.password}")
    private String SPRING_RABBITMQ_PASSWORD;

    @Value("${chat.rabbitmq.queue}")
    private String SPRING_RABBITMQ_QUEUE;

    @Value("${chat.rabbitmq.exchange}")
    private String SPRING_RABBITMQ_EXCHANGE;

    @Value("${chat.rabbitmq.routingkey}")
    private String SPRING_RABBITMQ_KEY;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(SPRING_RABBITMQ_HOST,SPRING_RABBITMQ_PORT);
        connectionFactory.setUsername(SPRING_RABBITMQ_USERNAME);
        connectionFactory.setPassword(SPRING_RABBITMQ_PASSWORD);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(SPRING_RABBITMQ_EXCHANGE);
        rabbitTemplate.setRoutingKey(SPRING_RABBITMQ_KEY);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(SPRING_RABBITMQ_EXCHANGE, true, false);
    }

    @Bean
    public Queue queue() {
        return new Queue(SPRING_RABBITMQ_QUEUE, true);
    }

    @Bean
    Binding exchangeBinding(DirectExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(SPRING_RABBITMQ_KEY);
    }
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}