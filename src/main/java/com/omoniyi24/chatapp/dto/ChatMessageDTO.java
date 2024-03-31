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
public class ChatMessageDTO {

    @NotBlank
    @Size(min = 1, max = 100, message = "Message too short or too Long")
    private String content;

}
