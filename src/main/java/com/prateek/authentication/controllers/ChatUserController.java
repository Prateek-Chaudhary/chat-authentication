package com.prateek.authentication.controllers;

import com.prateek.authentication.constants.ResponseBuilder;
import com.prateek.authentication.dto.request.ChatUserRequestDto;
import com.prateek.authentication.dto.request.LoginRequestDto;
import com.prateek.authentication.dto.response.ChatUserResponseDto;
import com.prateek.authentication.dto.response.ResponseDto;
import com.prateek.authentication.dto.response.TokenResponseDto;
import com.prateek.authentication.exceptions.UserNotFoundException;
import com.prateek.authentication.services.ChatUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("api/v1/chat-application/user/")
@RequiredArgsConstructor
@Validated
public class ChatUserController {

    private final ChatUserService chatUserService;

    @PostMapping("create")
    public ResponseEntity<ResponseDto<String>> createUser(@RequestBody @Valid ChatUserRequestDto chatUser,
                                                          WebRequest request) throws UserNotFoundException {
        String response = chatUserService.createUser(chatUser);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK, request);
    }

    @GetMapping("user-info")
    public ResponseEntity<ResponseDto<ChatUserResponseDto>> getUserInfo(@RequestParam String username,
                                                                        WebRequest request) {
        ChatUserResponseDto chatUser = chatUserService.getUserInfo(username);
        return ResponseBuilder.buildResponse(chatUser, HttpStatus.OK, request);
    }

    @PostMapping("login")
    public ResponseEntity<ResponseDto<TokenResponseDto>> loginUser(@RequestBody @Valid LoginRequestDto login,
                                                                   WebRequest request) {
        TokenResponseDto tokenResponse = chatUserService.login(login);
        return ResponseBuilder.buildResponse(tokenResponse, HttpStatus.OK, request);
    }
}
