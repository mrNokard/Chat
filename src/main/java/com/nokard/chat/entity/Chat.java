package com.nokard.chat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "chats")
public class Chat {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(
            name = "name",
            length = 100,
            nullable = false
    )
    private String name;

    @Column( name = "description" )
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
    private Set<Message> messages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
    private Set<ChatMember> memberProfiles;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "chats")
    private Set<User> users;
}