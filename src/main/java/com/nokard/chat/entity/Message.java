package com.nokard.chat.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "messages")
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
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
            name = "edited"
    )
    private Timestamp edited;

    @JoinColumn(name = "id_chat", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    @JoinColumn(name = "id_author", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @NonNull
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

    public Message(ChatMember profile){
        this.authorMember = profile;
        this.author = profile.getUser();
        this.chat = profile.getChat();
        this.sent = Timestamp.from(ZonedDateTime.now().toInstant());
    }

    public void editMessage() {
        this.edited = Timestamp.from(ZonedDateTime.now().toInstant());
    }
}
