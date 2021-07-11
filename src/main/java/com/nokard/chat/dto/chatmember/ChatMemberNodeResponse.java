package com.nokard.chat.dto.chatmember;

import com.nokard.chat.entity.ChatMember;
import com.nokard.chat.enums.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMemberNodeResponse {
    private Long id;
    private String login;
    private Roles role;

    public ChatMemberNodeResponse(ChatMember member){
        this.id = member.getUser().getId();
        this.login = member.getUser().getLogin();
        this.role = member.getRole();
    }
}
