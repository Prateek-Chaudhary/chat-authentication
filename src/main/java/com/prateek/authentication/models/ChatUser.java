package com.prateek.authentication.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "chat_user")
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToMany(fetch = FetchType.LAZY)
    private List<UserRole> roles;
}
