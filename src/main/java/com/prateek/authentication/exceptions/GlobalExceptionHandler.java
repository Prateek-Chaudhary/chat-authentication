package com.prateek.authentication.exceptions;

import com.prateek.authentication.constants.ErrorBuilder;
import com.prateek.authentication.dto.response.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.*;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException manv,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        manv.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(status).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> globalException(Exception ex, WebRequest request) {
        return ErrorBuilder.buildError(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> userNotFoundException(UserNotFoundException ex, WebRequest request) {
        return ErrorBuilder.buildError(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(JwtTokenException.class)
    public ResponseEntity<ErrorDto> jwtTokenException(JwtTokenException ex, WebRequest request) {
        return ErrorBuilder.buildError(ex, HttpStatus.UNAUTHORIZED, request);
    }
}
