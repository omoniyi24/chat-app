package com.omoniyi24.chatapp.service.impl;

import com.omoniyi24.chatapp.dto.ChatMessage;
import com.omoniyi24.chatapp.dto.ChatMessageDTO;
import com.omoniyi24.chatapp.entity.ChatRoom;
import com.omoniyi24.chatapp.entity.Message;
import com.omoniyi24.chatapp.service.ChatRoomService;
import com.omoniyi24.chatapp.service.ChatService;
import com.omoniyi24.chatapp.service.MessageService;
import com.omoniyi24.chatapp.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Value("${chat.rabbitmq.exchange}")
    private String exchange;

    @Value("${chat.rabbitmq.routingkey}")
    private String routingKey;


    @Override
    public void sendMessage(ChatMessageDTO message) {
        ChatMessage chatMessage = new ChatMessage();
        String currentUsername = userService.getCurrentUsername();
        chatMessage.setSender(currentUsername);
        chatMessage.setContent(message.getContent());
        rabbitTemplate.convertAndSend(exchange, routingKey, chatMessage);
    }

    @Override
    @RabbitListener(queues = "${chat.rabbitmq.queue}")
    public void receiveMessage(@Payload ChatMessage message) {
        System.out.println("Received message>>>>>>>>>>: " + message.toString());
        Message newMessage =  new Message();
        newMessage.setContent(message.getContent());
        newMessage.setUsername(message.getSender());
        newMessage.setTimeCreated(Instant.now());
        ChatRoom chatRoom = chatRoomService.getChatRoomByName("default");
        newMessage.setChatRoomId(chatRoom.getId());
        Message savedMessage = messageService.createMessage(newMessage);
        //send message websocket
        messagingTemplate.convertAndSend("/topic/public", savedMessage);

    }

}
