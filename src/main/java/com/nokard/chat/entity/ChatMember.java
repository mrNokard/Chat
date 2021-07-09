package com.nokard.chat.entity;

import com.nokard.chat.enums.Roles;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "chat_members")
public class ChatMember {
    @EmbeddedId
    private ChatMemberKey id;

    @Enumerated(EnumType.STRING)
    @Column(  name = "role" )
    private Roles role;

    @Column (name = "notify")
    private boolean notify;

    @MapsId("chatId")
    @JoinColumn(name = "id_chat")
    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    @MapsId("userId")
    @JoinColumn(name = "id_user")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorMember")
    private Set<Message> messages;

}

@Data
@Embeddable
@NoArgsConstructor
class ChatMemberKey implements Serializable {
    @Column(name = "id_chat")
    private Long chatId;

    @Column(name = "id_user")
    private Long userId;
}