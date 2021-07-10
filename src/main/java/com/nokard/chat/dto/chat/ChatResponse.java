package com.nokard.chat.dto.chat;

import com.nokard.chat.entity.Chat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatResponse {
    private Long id;
    private String name;
    private String description;

    public ChatResponse(Chat chat){
        this.id = chat.getId();
        this.name = chat.getName();
        this.description = chat.getDescription();
    }
}
