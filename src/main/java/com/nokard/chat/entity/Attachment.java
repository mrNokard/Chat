package com.nokard.chat.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "attachments")
public class Attachment extends  BaseEntity{
    @Column(
            name = "filename",
            nullable = false
    )
    private String filename;

    @JoinColumn(name = "id_message")
    @ManyToOne(fetch = FetchType.LAZY)
    private Message message;
}
