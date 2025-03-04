package com.relationship.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND,"PBH-101","Requested resource doesn't exist"),
    SYSTEM_ERRORS(HttpStatus.INTERNAL_SERVER_ERROR,"PBH-102","Internal Server Error"),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST,"PBH-104","Invalid input value"),
    BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED,"PBH-105","Username or password is incorrect"),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "PBH-106", "Username is already in use");;

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
