package com.relationship.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorReason {

    private final HttpStatus status;
    private final String code;
    private final String message;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime timestamp;
    private final Map<String, Object> response;

    private ErrorReason(HttpStatus status, String code, String message, Map<String, Object> response) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now();
        this.response = response;
    }

    // Factory method to create ErrorReason
    public static ErrorReason of(HttpStatus status, String code, String message, Map<String, Object> response) {
        return new ErrorReason(status, code, message, response);
    }

    // Getters
    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "ErrorReason{" +
                "status=" + status +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", response=" + response +
                '}';
    }
}

