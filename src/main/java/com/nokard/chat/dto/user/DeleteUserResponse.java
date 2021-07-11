package com.nokard.chat.dto.user;

import com.nokard.chat.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteUserResponse {
    private String login = null;
    private long timestamp = DateUtils.nowUnix();
}
