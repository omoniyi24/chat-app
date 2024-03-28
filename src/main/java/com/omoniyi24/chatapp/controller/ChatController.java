package com.omoniyi24.chatapp.controller;

import com.omoniyi24.chatapp.entity.ChatMessage;
import com.omoniyi24.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody ChatMessage message) {
        chatService.sendMessage(message);
        return "Message sent!";
    }
}
