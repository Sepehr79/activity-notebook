package com.sepehr.activity_notebook.controller.exception;

import com.sepehr.activity_notebook.controller.pojo.MessageEntity;
import com.sepehr.activity_notebook.model.exception.DuplicateUserNameException;
import com.sepehr.activity_notebook.model.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}