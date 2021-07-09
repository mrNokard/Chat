package com.nokard.chat.entity;

import com.nokard.chat.enums.Roles;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "chat_members")
public class ChatMember extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(  name = "role" )
    private Roles role;

    @Column (name = "notify")
    private boolean notify;

    @JoinColumn(name = "id_chat")
    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    @JoinColumn(name = "id_user")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private Set<Message> messages;

}
