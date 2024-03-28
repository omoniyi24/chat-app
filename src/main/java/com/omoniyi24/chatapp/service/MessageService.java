package com.omoniyi24.chatapp.service;

import com.omoniyi24.chatapp.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();
    Message createMessage(Message message);
}
