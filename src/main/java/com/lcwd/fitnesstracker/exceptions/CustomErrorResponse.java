package com.lcwd.fitnesstracker.exceptions;

public class CustomErrorResponse {
    private int statusCode;
    private String message;

    public CustomErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    // Getters
    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}