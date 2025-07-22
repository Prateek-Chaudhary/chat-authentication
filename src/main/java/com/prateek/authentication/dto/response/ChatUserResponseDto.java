package com.prateek.authentication.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChatUserResponseDto {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
}
