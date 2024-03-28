package com.omoniyi24.chatapp.service.impl;

import com.omoniyi24.chatapp.entity.ChatMessage;
import com.omoniyi24.chatapp.service.ChatService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${chat.rabbitmq.exchange}")
    private String exchange;

    @Value("${chat.rabbitmq.routingkey}")
    private String routingKey;


    @Override
    public void sendMessage(ChatMessage message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    @Override
    @RabbitListener(queues = "${chat.rabbitmq.queue}")
    public void receiveMessage(@Payload ChatMessage message) {
        System.out.println("Received message>>>>>>>>>>: " + message.toString());
    }
}
