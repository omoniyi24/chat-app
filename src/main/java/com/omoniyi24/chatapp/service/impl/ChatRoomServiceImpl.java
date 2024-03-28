package com.omoniyi24.chatapp.service.impl;

import com.omoniyi24.chatapp.entity.ChatMessage;
import com.omoniyi24.chatapp.entity.ChatRoom;
import com.omoniyi24.chatapp.entity.Message;
import com.omoniyi24.chatapp.enums.Status;
import com.omoniyi24.chatapp.repository.ChatRoomRepository;
import com.omoniyi24.chatapp.service.ChatRoomService;
import com.omoniyi24.chatapp.service.ChatService;
import com.omoniyi24.chatapp.service.MessageService;
import com.omoniyi24.chatapp.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom getChatRoomByName(String name) {
        return chatRoomRepository.findByNameAndStatus(name, Status.ACTIVE);
    }

    @Override
    public void createChatRoom(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
    }

    @PostConstruct()
    void createChatRoom(){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setId(1L);
        chatRoom.setName("default");
        chatRoom.setStatus(Status.ACTIVE);
        this.createChatRoom(chatRoom);
    }
}
