package com.example.student.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private int statusCode;
    private String details;
    
    public ErrorResponse() {}
    
    public ErrorResponse(LocalDateTime timestamp, String message, int statusCode, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.statusCode = statusCode;
        this.details = details;
    }
    
    // Getters & Setters
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
