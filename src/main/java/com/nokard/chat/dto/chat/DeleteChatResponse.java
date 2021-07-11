package com.nokard.chat.dto.chat;

import com.nokard.chat.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteChatResponse {
    private String name = null;
    private Long timestamp = DateUtils.nowUnix();
}
