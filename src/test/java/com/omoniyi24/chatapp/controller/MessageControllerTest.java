package com.omoniyi24.chatapp.controller;

import com.omoniyi24.chatapp.IntegrationTest;
import com.omoniyi24.chatapp.entity.Message;
import com.omoniyi24.chatapp.repository.MessageRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.Matchers.hasItem;


import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
public class MessageControllerTest {
    private static final Instant NOW = Instant.now();
    private static final Long MESSAGE_ID = 1L;
    private static final Long CHAT_ROOM_ID = 1L;
    private static final boolean DELETED_MESSAGE = false;
    private static final String MESSAGE_USERNAME = "omoniyi24";
    private static final String MESSAGE_CONTENT= "Hello World!";




    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMessagesMockMvc;

    private Message message = new Message();


    public static Message createEntity(EntityManager em) {
        Message message = new Message();
        message.setTimeCreated(NOW);
        message.setId(MESSAGE_ID);
        message.setUsername(MESSAGE_USERNAME);
        message.setContent(MESSAGE_CONTENT);
        message.setChatRoomId(CHAT_ROOM_ID);
        message.setDeleted(DELETED_MESSAGE);
        return message;
    }

    @BeforeEach
    public void initTest() {
        message = createEntity(em);
    }

    @Test
    @Transactional
    void getMessagesWhenDatabaseIsEmpty() throws Exception {
        int databaseSizeBeforeCreate = messageRepository.findAll().size();

        restMessagesMockMvc
                .perform(
                        get("/api/messages").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        List<Message> messgeList = messageRepository.findAll();
        assertThat(messgeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getMessagesWhenDatabaseHasData() throws Exception {
        //add a message to the database
        messageRepository.save(this.message);
        int databaseSizeBeforeCreate = messageRepository.findAll().size();

        restMessagesMockMvc
                .perform(
                        get("/api/messages").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        List<Message> messgeList = messageRepository.findAll();
        assertThat(messgeList).hasSize(databaseSizeBeforeCreate);
        Message messageResponse = messgeList.get(0);
        assertThat(messageResponse.getId()).isEqualTo(MESSAGE_ID);
        assertThat(messageResponse.getTimeCreated()).isEqualTo(NOW);
        assertThat(messageResponse.getUsername()).isEqualTo(MESSAGE_USERNAME);
        assertThat(messageResponse.getContent()).isEqualTo(MESSAGE_CONTENT);
        assertThat(messageResponse.isDeleted()).isEqualTo(DELETED_MESSAGE);
    }


    @Test
    @Transactional
    void getPageableMessagesWhenDatabaseHasData() throws Exception {
        //add a messages to the database
        for (int i = 0; i < 5; i++){
            this.message.setId(Long.valueOf(i+2));
            messageRepository.saveAndFlush(this.message);
        }

        restMessagesMockMvc
                .perform(get("/api/messages" + "?page=0&size=2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*].username").value(hasItem(MESSAGE_USERNAME)))
                .andExpect(jsonPath("$.[*].content").value(hasItem(MESSAGE_CONTENT)))
                .andExpect(jsonPath("$.[*].chatRoomId").value(hasItem(CHAT_ROOM_ID.intValue())))
                .andExpect(jsonPath("$.[*].timeCreated").value(hasItem(NOW.toString())))
                .andExpect(jsonPath("$.[*].deleted").value(hasItem(DELETED_MESSAGE)));
    }

}

