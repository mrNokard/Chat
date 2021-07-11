package com.nokard.chat.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequest {
    private Long id = null;
    private String login = null;
    private String bio = null;
}
