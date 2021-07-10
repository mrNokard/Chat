package com.nokard.chat.dto.user;

import com.nokard.chat.dto.chat.ChatResponse;
import com.nokard.chat.entity.ChatMember;
import com.nokard.chat.entity.User;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String login;
    private String bio;
    private Set<ChatResponse> chats;

    public UserResponse(User user){
        this.id = user.getId();
        this.bio = user.getBio();
        this.login = user.getLogin();
        this.chats = user.getMemberProfiles()
                .stream()
                .map(ChatMember::getChat)
                .map(ChatResponse::new)
                .collect(Collectors.toSet());
    }
}
