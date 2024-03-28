package com.omoniyi24.chatapp.repository;

import com.omoniyi24.chatapp.entity.ChatRoom;
import com.omoniyi24.chatapp.entity.Message;
import com.omoniyi24.chatapp.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    ChatRoom findByNameAndStatus(String name, Status status);
}
