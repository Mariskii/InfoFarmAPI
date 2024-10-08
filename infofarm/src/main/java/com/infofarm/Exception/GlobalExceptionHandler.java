package com.infofarm.Exception;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Exception.Errors.NotEnoughtQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> IdNotFound(IdNotFoundException exception)
    {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),exception.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(NotEnoughtQuantityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> NotEnought(NotEnoughtQuantityException exception)
    {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),exception.getMessage());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
