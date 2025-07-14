package com.prateek.authentication.models;

import com.prateek.authentication.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roleId;
    @Enumerated(EnumType.STRING)
    private Roles role;
}
