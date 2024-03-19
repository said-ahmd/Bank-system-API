package org.fawry.bankapisystem.dto;

public class GeneralResponseDto {
    boolean success;
    Object body;

    public GeneralResponseDto(boolean success, Object body) {
        this.success = success;
        this.body = body;
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
