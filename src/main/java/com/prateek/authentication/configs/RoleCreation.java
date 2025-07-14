package com.prateek.authentication.configs;

import com.prateek.authentication.enums.Roles;
import com.prateek.authentication.models.UserRole;
import com.prateek.authentication.repositories.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleCreation {

    private final UserRoleRepository userRoleRepository;

    @PostConstruct
    public void createRoles() {
        if(userRoleRepository.count() > 0) return;

        UserRole role1 = new UserRole();
        role1.setRole(Roles.USER);
        userRoleRepository.save(role1);

        UserRole role2 = new UserRole();
        role2.setRole(Roles.ADMIN);
        userRoleRepository.save(role2);
    }
}
