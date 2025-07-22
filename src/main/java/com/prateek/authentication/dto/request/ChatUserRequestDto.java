package com.prateek.authentication.dto.request;

import com.prateek.authentication.constants.RegexBuilder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChatUserRequestDto {

    @NotNull(message = "FirstName is required!")
    @Pattern(regexp = RegexBuilder.NAME_REGEX, message = "Please enter valid first name!")
    private String firstName;

    @NotNull(message = "LastName is required!")
    @Pattern(regexp = RegexBuilder.NAME_REGEX, message = "Please enter valid last name!")
    private String lastName;

    @NotNull(message = "Email is required!")
    @Pattern(regexp = RegexBuilder.EMAIL_REGEX, message = "Please enter valid email!")
    private String email;

    @NotNull(message = "Phone Number is required!")
    @Pattern(regexp = RegexBuilder.PHONE_REGEX, message = "Please enter valid phone number!")
    private String phone;

    @NotNull(message = "Username is required!")
    @Pattern(regexp = RegexBuilder.USERNAME_REGEX, message = "Please enter valid username!")
    private String username;

    @NotNull(message = "Password is required!")
    @Pattern(regexp = RegexBuilder.PASSWORD_REGEX, message = "Please enter valid password!")
    private String password;
}
