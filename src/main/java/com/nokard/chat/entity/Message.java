package com.nokard.chat.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "messages")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Message extends BaseEntity {
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

    @JoinColumn(name = "id_chat")
    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    @JoinColumn(name = "id_author")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatMember author;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "message"
    )
    private Set<Attachment> attachments;
}
