package com.omoniyi24.chatapp.repository;

import com.omoniyi24.chatapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<List<Message>> findAllByDeleted(Pageable pageable, boolean deleted);
}
