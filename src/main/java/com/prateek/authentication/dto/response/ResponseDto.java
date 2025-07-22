package com.prateek.authentication.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ResponseDto <T> {

    private String uri;
    private HttpStatus status;
    private LocalDateTime responseTime;
    private T response;
}
