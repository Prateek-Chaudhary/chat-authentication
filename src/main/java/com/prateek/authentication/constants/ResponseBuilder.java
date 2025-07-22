package com.prateek.authentication.constants;

import com.prateek.authentication.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

public class ResponseBuilder {

    public static <T> ResponseEntity<ResponseDto<T>> buildResponse(T data, HttpStatus status, WebRequest request) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setUri(request != null ? request.getDescription(false) : "N/A");
        response.setResponse(data);
        response.setResponseTime(LocalDateTime.now());
        response.setStatus(status);
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<ResponseDto<T>> buildResponse(T data, HttpStatus status) {
        return buildResponse(data, status, null);
    }
}
