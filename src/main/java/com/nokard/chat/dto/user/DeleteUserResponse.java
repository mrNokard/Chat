package com.nokard.chat.dto.user;

import com.nokard.chat.dto.BaseDeleteResponse;
import com.nokard.chat.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteUserResponse extends BaseDeleteResponse {
    private String login;
}
