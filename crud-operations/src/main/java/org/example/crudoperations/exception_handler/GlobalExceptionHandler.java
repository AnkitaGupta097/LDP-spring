package org.example.crudoperations.exception_handler;

import org.example.crudoperations.exceptions.ProductNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.example.crudoperations.response.ExceptionResponse;
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    ExceptionResponse exceptionResponse;

    @ExceptionHandler
    ResponseEntity<ExceptionResponse> productNotFound(ProductNotExist exception){


        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }
}
