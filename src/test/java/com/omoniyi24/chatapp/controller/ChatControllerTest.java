package com.omoniyi24.chatapp.controller;

import com.omoniyi24.chatapp.IntegrationTest;
import com.omoniyi24.chatapp.TestUtil;
import com.omoniyi24.chatapp.dto.ChatMessage;
import com.omoniyi24.chatapp.dto.ChatMessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
public class ChatControllerTest {

    private static final Instant NOW = Instant.now();
    private static final Long MESSAGE_ID = 1L;
    private static final Long CHAT_ROOM_ID = 1L;
    private static final boolean DELETED_MESSAGE = false;
    private static final String MESSAGE_USERNAME = "user";
    private static final String MESSAGE_CONTENT= "Hello World!";
    private static final String MESSAGE_CONTENT_TOO_LONG= "message too long message too long message too long message too" +
            " long message too long message too long message too long message too long message too long message too long " +
            " long message too long message too long message too long message too long message too long message too long " +
            " long message too long message too long message too long message too long message too long message too long " +
            "message too long";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    ChatMessage message = new ChatMessage();

    ChatMessageDTO messageDTO = new ChatMessageDTO();


    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        message.setSender(MESSAGE_USERNAME);
        message.setContent(MESSAGE_CONTENT);
        messageDTO.setContent(MESSAGE_CONTENT);
    }

    @Test
    @Transactional
    void sendMessageWhenRequestIsValid() throws Exception {

        mockMvc
                .perform(
                        post("/chat/send").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(this.messageDTO))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message").value("sent"));
        verify(rabbitTemplate).convertAndSend("chatExchange", "chatRoutingKey", message);

    }

    @Test
    @Transactional
    void sendMessageWhenRequestContentIsTooLong() throws Exception {

        messageDTO.setContent(MESSAGE_CONTENT_TOO_LONG);

        mockMvc
                .perform(
                        post("/chat/send").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(this.messageDTO))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.errors").value(hasItem("content: Message too short or too Long")));
        verify(rabbitTemplate, never()).convertAndSend("chatExchange", "chatRoutingKey", message);
    }

}
