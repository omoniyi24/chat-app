package com.omoniyi24.chatapp.entity;

import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    @Null
    private String sender;

    @NotBlank
    @Size(min = 1, max = 100, message = "Message too short or too Long")
    private String content;

}
