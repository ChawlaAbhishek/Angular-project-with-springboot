package com.hostbooks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException rnfe){
        MyErrorDetails er = new MyErrorDetails(LocalDateTime.now(), rnfe.getMessage(), false);
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeException.class)
    public  ResponseEntity<?> employeeExceptionHandler(EmployeeException ee){
        MyErrorDetails er=new MyErrorDetails(LocalDateTime.now(),ee.getMessage(),false);
        return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
    }
}
