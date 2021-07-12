package com.nokard.chat.dto.chatmember;

import com.nokard.chat.dto.BaseDeleteResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberDeleteResponse extends BaseDeleteResponse {
    private Long chatId;
    private Long UserId;
}
