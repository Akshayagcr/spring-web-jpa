package com.learning.spring_web_jpa.rest.exceptionhandler;

import com.learning.spring_web_jpa.domain.dto.ErrorResponse;
import com.learning.spring_web_jpa.domain.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class TodoAppExceptionHandler {

    @ExceptionHandler(exception = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException userNotFoundException){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "USER_NOT_FOUND", "User not found", "error");
    }

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Parameter invalid", methodArgumentNotValidException.getMessage(), "error");
    }

    @ExceptionHandler(exception = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Illegal argument", methodArgumentTypeMismatchException.getMostSpecificCause().getMessage(), "error");
    }

    @ExceptionHandler(exception = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Illegal argument", illegalArgumentException.getMessage(), "error");
    }

    @ExceptionHandler(exception = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception exception){
        log.error("Error : ", exception);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR", exception.getMessage(), "error");
    }
}
