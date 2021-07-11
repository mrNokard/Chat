package com.nokard.chat.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "attachments")
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(
            name = "filename",
            nullable = false
    )
    private String filename;

    @NonNull
    @JoinColumn(name = "id_message")
    @ManyToOne(fetch = FetchType.LAZY)
    private Message message;
}
