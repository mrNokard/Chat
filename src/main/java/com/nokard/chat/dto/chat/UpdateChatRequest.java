package com.nokard.chat.dto.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateChatRequest {
    private Long id = null;
    private String name = null;
    private String description = null;
}
