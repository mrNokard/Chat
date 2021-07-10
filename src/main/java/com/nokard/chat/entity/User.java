package com.nokard.chat.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<Chat> chats;
}
