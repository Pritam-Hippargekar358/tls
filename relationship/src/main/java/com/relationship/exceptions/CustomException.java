package com.relationship.exceptions;

import com.relationship.utils.ResponseUtils;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final Map<String, Object> response;

    public CustomException(Map<String, Object> response) {
        super(response.get(ResponseUtils.MESSAGE).toString());
        this.status = HttpStatus.BAD_REQUEST;
        this.message = response.get(ResponseUtils.MESSAGE).toString();
        this.response = response;
    }

    public CustomException(HttpStatus status, Map<String, Object> response) {
        super(response.get(ResponseUtils.MESSAGE).toString());
        this.status = status;
        this.message = response.get(ResponseUtils.MESSAGE).toString();
        this.response = response;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getResponse() {
        return response;
    }

}
