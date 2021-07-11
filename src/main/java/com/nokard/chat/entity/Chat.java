package com.nokard.chat.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "chats")
@NoArgsConstructor
public class Chat {
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

    @JoinTable(
            name = "chat_members",
            joinColumns = { @JoinColumn(name = "id_chat")},
            inverseJoinColumns = { @JoinColumn(name = "id_user") }
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> users;
}