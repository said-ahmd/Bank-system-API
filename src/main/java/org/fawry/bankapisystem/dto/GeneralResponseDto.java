package org.fawry.bankapisystem.dto;

public class GeneralResponseDto {
    boolean success;
    Object body;
    Object error;

    public GeneralResponseDto() {
    }
    public GeneralResponseDto(boolean success) {
        this.success=success;
    }
    public GeneralResponseDto(boolean success, Object body) {
        this.success = success;
        this.body = body;
    }
    public GeneralResponseDto(boolean success, Object body, Object error) {
        this.success = success;
        this.body = body;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

}
