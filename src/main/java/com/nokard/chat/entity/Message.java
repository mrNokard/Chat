package com.nokard.chat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "messages")
@NoArgsConstructor
public class Message {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name="content",
            nullable = false
    )
    private String content;
    @Column(
            name = "sent",
            nullable = false
    )
    private Timestamp sent;
    @Column(
            name = "edited",
            nullable = false
    )
    private Timestamp edited;

    @JoinColumn(name = "id_chat", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    @JoinColumn(name = "id_author", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @JoinColumns({
            @JoinColumn(name = "id_chat"),
            @JoinColumn(name = "id_author")
    })
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatMember authorMember;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "message"
    )
    private Set<Attachment> attachments;
}
