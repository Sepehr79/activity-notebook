package com.sepehr.activity_notebook.model.exception;

public class UserNotFoundException extends RuntimeException {

    private final String userName;

    public UserNotFoundException(String userName) {
        super();
        this.userName = userName;
    }

    @Override
    public String getMessage() {
        return "User not found with the given username: " + userName;
    }
}
