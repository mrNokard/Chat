package com.nokard.chat.entity;

import com.nokard.chat.enums.Roles;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "chat_members")
@NoArgsConstructor
public class ChatMember {
    @EmbeddedId
    private ChatMemberKey id = new ChatMemberKey();

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(  name = "role" )
    private Roles role;

    @Column (name = "notify")
    private boolean notify;

    @NonNull
    @MapsId("chatId")
    @JoinColumn(name = "id_chat", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    @NonNull
    @MapsId("userId")
    @JoinColumn(name = "id_user", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorMember")
    private Set<Message> messages;

    public ChatMember(Chat chat, User user){
        this.id = new ChatMemberKey(chat.getId(), user.getId());
        this.chat = chat;
        this.user = user;
    }
}

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
class ChatMemberKey implements Serializable {
    @Column(name = "id_chat")
    private Long chatId = 0L;

    @Column(name = "id_user")
    private Long userId = 0L;
}