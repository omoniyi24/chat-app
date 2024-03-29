package com.omoniyi24.chatapp.controller;

import com.omoniyi24.chatapp.entity.Message;
import com.omoniyi24.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Message> getAllMessages(Pageable pageable) {
        return messageService.getAllMessages(pageable);
    }
}
