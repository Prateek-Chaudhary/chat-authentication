package com.prateek.authentication.dto.request;

import com.prateek.authentication.constants.RegexBuilder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoginRequestDto {

    @NotEmpty(message = "Username is mandatory!")
    @Pattern(regexp = RegexBuilder.USERNAME_REGEX)
    private String username;

    @NotNull(message = "Password is mandatory!")
    @Pattern(regexp = RegexBuilder.PASSWORD_REGEX)
    private String password;
}
