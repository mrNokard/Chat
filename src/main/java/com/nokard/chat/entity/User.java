package com.nokard.chat.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
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
}
