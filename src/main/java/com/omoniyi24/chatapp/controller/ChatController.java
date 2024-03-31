package com.omoniyi24.chatapp.controller;

import com.omoniyi24.chatapp.dto.ChatMessageDTO;
import com.omoniyi24.chatapp.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMessage(@Valid @RequestBody ChatMessageDTO message) {
        chatService.sendMessage(message);
        Map<String, String> response = new HashMap<>();
        response.put("message", "sent");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
