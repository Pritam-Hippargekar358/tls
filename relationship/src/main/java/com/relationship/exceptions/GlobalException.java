package com.relationship.exceptions;

import com.relationship.enums.ErrorCode;
import java.util.HashMap;
import java.util.Map;

public class GlobalException extends RuntimeException{
    protected final ErrorReason errorReason;

    public GlobalException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorReason = ErrorReason.of(
                errorCode.getStatus(),
                errorCode.getCode(),
                errorCode.getMessage(),
                new HashMap<>()
        );
    }

    public GlobalException(ErrorCode errorCode, Map<String, Object> response) {
        super(errorCode.getMessage());
        this.errorReason = ErrorReason.of(
                errorCode.getStatus(),
                errorCode.getCode(),
                errorCode.getMessage(),
                response
        );
    }

    public ErrorReason getErrorReason() {
        return errorReason;
    }

}
