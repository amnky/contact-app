package com.techlabs.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContactResponseExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ContactErrorResponse> handleTransactionException(UserException exc) {

        // create a Student Error Message
        ContactErrorResponse error = new ContactErrorResponse();

        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ContactErrorResponse> handleEmailNotSendException(ContactDetailsException exc) {

        // create a Student Error Message
        ContactErrorResponse error = new ContactErrorResponse();

        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ContactErrorResponse> BadCredentials(BadCredentialsException exc) {

        // create a Student Error Message
        ContactErrorResponse error = new ContactErrorResponse();

        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ContactErrorResponse> handleException(ContactApiException exc) {

        // create a Student Error Message
        ContactErrorResponse error = new ContactErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ContactErrorResponse> handleException(AccessDeniedException exc) {

        // create a Student Error Message
        ContactErrorResponse error = new ContactErrorResponse();

        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setMessage(exc.getClass().getSimpleName());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ContactErrorResponse> handleException(Exception exc) {

        // create a Student Error Message
        ContactErrorResponse error = new ContactErrorResponse();
        System.out.println("unknown error");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getClass().getSimpleName());
        exc.printStackTrace();
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler
    public ResponseEntity<ContactErrorResponse> handleException(NoContactRecordFoundException exc) {

        // create a Student Error Message
        ContactErrorResponse error = new ContactErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}