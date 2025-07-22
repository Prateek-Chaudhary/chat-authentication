package com.prateek.authentication.constants;

import com.prateek.authentication.dto.response.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

public class ErrorBuilder {

    public static ResponseEntity<ErrorDto> buildError(Exception ex, HttpStatus status, WebRequest request) {
        ErrorDto error = ErrorDto.builder()
                .errorMessage(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .status(status)
                .uri(request != null ? request.getDescription(false) : "N/A")
                .build();

        return ResponseEntity.status(status).body(error);
    }

    public static ResponseEntity<ErrorDto> buildError(Exception ex, HttpStatus status) {
        return buildError(ex, status, null);
    }
}
