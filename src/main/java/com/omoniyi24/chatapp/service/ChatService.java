package com.omoniyi24.chatapp.service;

import com.omoniyi24.chatapp.entity.ChatMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public interface ChatService {

    void sendMessage(ChatMessage message);

    void receiveMessage(ChatMessage message);
}
