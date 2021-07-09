package com.nokard.chat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "attachments")
public class Attachment {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "filename",
            nullable = false
    )
    private String filename;

    @JoinColumn(name = "id_message")
    @ManyToOne(fetch = FetchType.LAZY)
    private Message message;
}
