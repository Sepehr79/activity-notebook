package com.sepehr.activity_notebook.controller.exception_manager;

import com.sepehr.activity_notebook.controller.pojo.MessageEntity;
import com.sepehr.activity_notebook.model.exception.DuplicateUserNameException;
import com.sepehr.activity_notebook.model.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageEntity> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(
                new MessageEntity(userNotFoundException.getMessage(), ""),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateUserNameException.class)
    public ResponseEntity<MessageEntity> handleDuplicateUserNameException(DuplicateUserNameException duplicateUserNameException){
        return new ResponseEntity<>(
                new MessageEntity(duplicateUserNameException.getMessage(), ""),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<MessageEntity> handleIndexOutOfBoundsException(IndexOutOfBoundsException index){
        return new ResponseEntity<>(
                new MessageEntity(index.getMessage(), ""),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<MessageEntity> handleNumberFormatException(NumberFormatException numberFormatException){
        return new ResponseEntity<>(
                new MessageEntity(numberFormatException.getMessage(), "Wrong input format"),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageEntity> handleNullPointerException(ConstraintViolationException constraintViolationException){
        return new ResponseEntity<>(
            new MessageEntity("Some fields are required", constraintViolationException.getLocalizedMessage()),
            HttpStatus.BAD_REQUEST
        );
    }
}
