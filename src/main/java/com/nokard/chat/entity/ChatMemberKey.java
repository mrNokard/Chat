package com.nokard.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberKey implements Serializable {
    @Column(name = "id_chat")
    private Long chatId = 0L;

    @Column(name = "id_user")
    private Long userId = 0L;
}
