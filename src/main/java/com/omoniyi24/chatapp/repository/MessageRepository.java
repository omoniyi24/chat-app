package com.omoniyi24.chatapp.repository;

import com.omoniyi24.chatapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
