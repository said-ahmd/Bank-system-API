package org.fawry.bankapisystem.dto;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class Error {
    private HttpStatus httpStatus;
    private String message;
    private Timestamp timestamp;

    public Error(HttpStatus httpStatus, String message, Timestamp timestamp) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
