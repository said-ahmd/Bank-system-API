package org.fawry.bankapisystem.mapper;

import org.fawry.bankapisystem.dto.error.Error;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ErrorMapper {
    public Error toResponse(HttpStatus status,String message){
        return new Error(
                status,
                message,
                new Timestamp(System.currentTimeMillis())
        );
    }
}
