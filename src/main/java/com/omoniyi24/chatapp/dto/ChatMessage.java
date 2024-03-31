package com.omoniyi24.chatapp.dto;

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

    private String sender;

    private String content;

}
