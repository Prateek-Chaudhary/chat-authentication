package com.prateek.authentication.services.implementation;

import com.prateek.authentication.dto.request.ChatUserRequestDto;
import com.prateek.authentication.dto.request.LoginRequestDto;
import com.prateek.authentication.dto.response.ChatUserResponseDto;
import com.prateek.authentication.dto.response.TokenResponseDto;
import com.prateek.authentication.enums.Roles;
import com.prateek.authentication.exceptions.UserNotFoundException;
import com.prateek.authentication.models.ChatUser;
import com.prateek.authentication.models.UserRole;
import com.prateek.authentication.repositories.ChatUserRepository;
import com.prateek.authentication.repositories.UserRoleRepository;
import com.prateek.authentication.services.ChatUserService;
import com.prateek.authentication.services.CustomUserService;
import com.prateek.authentication.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatUserServiceImpl implements ChatUserService {

    private final ChatUserRepository chatUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserService userService;

    @Override
    public String createUser(ChatUserRequestDto chatUser) throws UserNotFoundException {
        ChatUser user = chatUserDtoToUser(chatUser);
        chatUserRepository.save(user);
        return "User created!";
    }

    private ChatUser chatUserDtoToUser(ChatUserRequestDto chatUser) throws UserNotFoundException {
        ChatUser user = new ChatUser();
        user.setUsername(chatUser.getUsername());
        user.setPassword(passwordEncoder.encode(chatUser.getPassword()));

        UserRole role = userRoleRepository.findByRole(Roles.USER);
        if(role == null) throw new UserNotFoundException("Role not present!");
        List<UserRole> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        return user;
    }

    @Override
    public ChatUserResponseDto getUserInfo(String username) {
        ChatUser user = chatUserRepository.findByUsername(username);
        return chatUserToUserInfo(user);
    }

    private ChatUserResponseDto chatUserToUserInfo(ChatUser user) {
        return ChatUserResponseDto.builder()
                .firstName("")
                .lastName("")
                .username(user.getUsername())
                .email(user.getUserId().toString())
                .phone("")
                .build();
    }

    @Override
    public TokenResponseDto login(LoginRequestDto login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        if(!authentication.isAuthenticated()) throw new BadCredentialsException("Username or Password is incorrect!");
        UserDetails user = userService.loadUserByUsername(login.getUsername());
        ChatUser chatUser = chatUserRepository.findByUsername(login.getUsername());
        String accessToken = jwtService.generateAccessToken(user, chatUser.getUserId().toString());
        String refreshToken = jwtService.generateRefreshToken(user, chatUser.getUserId().toString());
        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
