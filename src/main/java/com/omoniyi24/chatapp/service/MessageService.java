package com.omoniyi24.chatapp.service;

import com.omoniyi24.chatapp.entity.Message;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages(Pageable pageable);
    Message createMessage(Message message);
    void deleteMessage(Long messageId);
}
