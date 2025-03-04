package com.relationship.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";
    public static final String ERROR = "error";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAILED = "failed";

    public static Map<String, Object> createErrorResponse(String message, Object object) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put(STATUS, STATUS_FAILED);
        successResponse.put(MESSAGE, message);
        successResponse.put(ERROR, object);
        return successResponse;
    }

    public static Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(STATUS, STATUS_FAILED);
        errorResponse.put(MESSAGE, message);
        return errorResponse;
    }

    public static Map<String, Object> createSuccessResponse(String message, Object object) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put(STATUS, STATUS_SUCCESS);
        successResponse.put(MESSAGE, message);
        successResponse.put(DATA, object);
        return successResponse;
    }

    public static Map<String, Object> createSuccessResponse(String message) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put(STATUS, STATUS_SUCCESS);
        successResponse.put(MESSAGE, message);
        return successResponse;
    }
}
