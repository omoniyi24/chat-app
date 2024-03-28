package com.omoniyi24.chatapp.service;

import com.omoniyi24.chatapp.entity.ChatMessage;
import com.omoniyi24.chatapp.entity.ChatRoom;

public interface ChatRoomService {

    ChatRoom getChatRoomByName(String name);

    void createChatRoom(ChatRoom chatRoom);
}
