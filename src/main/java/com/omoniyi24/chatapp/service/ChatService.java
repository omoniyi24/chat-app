package com.omoniyi24.chatapp.service;

import com.omoniyi24.chatapp.dto.ChatMessage;
import com.omoniyi24.chatapp.dto.ChatMessageDTO;

public interface ChatService {

    void sendMessage(ChatMessageDTO message);

    void receiveMessage(ChatMessage message);
}
