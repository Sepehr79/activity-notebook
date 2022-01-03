package com.sepehr.activity_notebook.model.exception;

import lombok.Getter;

public class DuplicateUserNameException extends Exception{

    @Getter
    private final String userName;

    public DuplicateUserNameException(String message, String userName) {
        super(message);
        this.userName = userName;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " [UserName: " + userName + "]";
    }
}
