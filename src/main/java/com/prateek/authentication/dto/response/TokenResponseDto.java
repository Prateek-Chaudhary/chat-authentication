package com.prateek.authentication.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;
}
