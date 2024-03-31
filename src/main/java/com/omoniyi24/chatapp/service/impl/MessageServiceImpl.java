package com.omoniyi24.chatapp.service.impl;

import com.omoniyi24.chatapp.entity.Message;
import com.omoniyi24.chatapp.repository.MessageRepository;
import com.omoniyi24.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> getAllMessages(Long chatRoomId, Pageable pageable) {
        return messageRepository.findAllByChatRoomIdAndDeleted(pageable, chatRoomId, false).get();
    }

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Long messageId) {
        Optional<Message> byId = messageRepository.findById(messageId);
        if(byId.isPresent()){
            Message message = byId.get();
            message.setDeleted(true);
            messageRepository.save(message);
        }
    }
}
