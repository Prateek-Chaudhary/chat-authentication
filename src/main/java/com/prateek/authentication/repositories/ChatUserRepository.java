package com.prateek.authentication.repositories;

import com.prateek.authentication.models.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ChatUserRepository extends JpaRepository<ChatUser, UUID> {

    ChatUser findByUsername(String username);
}
