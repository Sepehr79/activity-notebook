package com.sepehr.activity_notebook.model.exception;

import lombok.Getter;

public class DuplicateUserNameException extends RuntimeException{

    @Getter
    private final String userName;

    private static final String MESSAGE = "Username already taken with the given username.";

    public DuplicateUserNameException(String userName) {
        this.userName = userName;
    }

    @Override
    public String getMessage() {
        return MESSAGE + " [UserName: " + userName + "]";
    }
}
