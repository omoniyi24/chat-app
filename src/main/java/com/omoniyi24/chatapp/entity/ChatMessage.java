package com.omoniyi24.chatapp.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    private String sender;

    private String receiver;
    private String content;

//    public ChatMessage() {
//    }
//
//    @JsonCreator
//    public ChatMessage(@JsonProperty("sender") String sender, @JsonProperty("receiver") String receiver, @JsonProperty("content") String content) {
//        this.sender = sender;
//        this.receiver = receiver;
//        this.content = content;
//    }

//    public String getSender() {
//        return sender;
//    }
//
//    public void setSender(String sender) {
//        this.sender = sender;
//    }
//
//    public String getReceiver() {
//        return receiver;
//    }
//
//    public void setReceiver(String reciever) {
//        this.receiver = reciever;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
}
