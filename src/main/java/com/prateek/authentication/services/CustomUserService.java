package com.prateek.authentication.services;

import com.prateek.authentication.models.ChatUser;
import com.prateek.authentication.repositories.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final ChatUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ChatUser chatUser = userRepository.findByUsername(username);
        if(chatUser != null) return new User(chatUser.getUsername(), chatUser.getPassword(), chatUser.getAuthorities());
        throw  new UsernameNotFoundException("User not found!");
    }
}
