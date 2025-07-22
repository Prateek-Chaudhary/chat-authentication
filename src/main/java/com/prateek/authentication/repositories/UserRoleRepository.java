package com.prateek.authentication.repositories;

import com.prateek.authentication.enums.Roles;
import com.prateek.authentication.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    UserRole findByRole(Roles role);
}
