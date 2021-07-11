package com.nokard.chat.dto.chatmember;

import com.nokard.chat.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddChatMemberRequest {
    private Long idMember;
    private Long idChat;
    private Roles role;
    private Boolean notify;
}
