package com.nokard.chat.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(
            name = "login",
            nullable = false,
            length = 100
    )
    private String login;
    @Column(
            name = "bio",
            length = 100
    )
    private String bio;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<ChatMember> memberProfiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private Set<Message> messages;

    @JoinTable(
            name = "chat_members",
            joinColumns = { @JoinColumn(name = "id_user")},
            inverseJoinColumns = { @JoinColumn(name = "id_chat") }
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Chat> chats;
}
