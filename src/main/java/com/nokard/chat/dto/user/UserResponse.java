package com.nokard.chat.dto.user;

import com.nokard.chat.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String login;
    private String bio;

    public UserResponse(User user){
        this.id = user.getId();
        this.login = user.getLogin();
        this.bio = user.getBio();
    }
}
