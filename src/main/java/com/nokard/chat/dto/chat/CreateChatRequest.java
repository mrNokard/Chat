package com.nokard.chat.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateChatRequest {
    private String name;
    private String description;
    //TODO: Login system
    private Long creatorId;
}
