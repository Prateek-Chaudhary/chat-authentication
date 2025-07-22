package com.prateek.authentication.services;

import com.prateek.authentication.dto.request.ChatUserRequestDto;
import com.prateek.authentication.dto.request.LoginRequestDto;
import com.prateek.authentication.dto.response.ChatUserResponseDto;
import com.prateek.authentication.dto.response.TokenResponseDto;
import com.prateek.authentication.exceptions.UserNotFoundException;
import jakarta.validation.Valid;

public interface ChatUserService {

    String createUser(ChatUserRequestDto chatUser) throws UserNotFoundException;

    ChatUserResponseDto getUserInfo(String username);

    TokenResponseDto login(@Valid LoginRequestDto login);
}
