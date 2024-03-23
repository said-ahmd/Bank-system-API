package org.fawry.bankapisystem.exceptions;


import org.fawry.bankapisystem.dto.error.Error;
import org.fawry.bankapisystem.mapper.ErrorMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {
    final private ErrorMapper errorMapper;

    private Map<String ,String> errors;

    public ExceptionsHandler(ErrorMapper errorMapper) {
        this.errorMapper = errorMapper;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handelException(MethodArgumentNotValidException exception){

        List<Error> response = exception.getFieldErrors()
                .stream()
                .map(ex->errorMapper.toResponse(HttpStatus.BAD_REQUEST,ex.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleException(BadCredentialsException exception){

        Error response = errorMapper.toResponse(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return ResponseEntity.badRequest().body(response);

    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handelException(IllegalArgumentException exception){
        Error response = errorMapper.toResponse(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(response);
    }
}
