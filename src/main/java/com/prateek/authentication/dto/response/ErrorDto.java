package com.prateek.authentication.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ErrorDto {

    private String uri;
    private HttpStatus status;
    private LocalDateTime errorTime;
    private String errorMessage;
}
