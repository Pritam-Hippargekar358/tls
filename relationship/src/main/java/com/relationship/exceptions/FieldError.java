package com.relationship.exceptions;

public class FieldError {
    private String field;
    private String value;
    private String reason;

    private FieldError(String field, String value, String reason) {
        this.field = field;
        this.value = value;
        this.reason = reason;
    }
}
