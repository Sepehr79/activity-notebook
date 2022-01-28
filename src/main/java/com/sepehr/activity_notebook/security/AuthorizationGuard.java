package com.sepehr.activity_notebook.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationGuard {

    public boolean checkUserId(Authentication authentication, String userName) {
        return authentication.getName().equals(userName);
    }

}
