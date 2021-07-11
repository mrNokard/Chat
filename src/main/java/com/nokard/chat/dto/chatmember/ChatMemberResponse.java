package com.nokard.chat.dto.chatmember;

import com.nokard.chat.entity.ChatMember;
import com.nokard.chat.enums.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMemberResponse {
    private Long id;
    private String login;
    private String bio;
    private Roles role;
    private boolean notify;

    public ChatMemberResponse(ChatMember member){
        this.id = member.getUser().getId();
        this.login = member.getUser().getLogin();
        this.bio = member.getUser().getBio();
        this.role = member.getRole();
        this.notify = member.isNotify();
    }
}
