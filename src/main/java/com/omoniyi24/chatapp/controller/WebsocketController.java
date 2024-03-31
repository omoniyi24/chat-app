package com.omoniyi24.chatapp.controller;

import com.omoniyi24.chatapp.dto.ChatMessageDTO;
import com.omoniyi24.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(ChatMessageDTO chatMessage) {
        chatService.sendMessage(chatMessage);
        return chatMessage;
    }
}
