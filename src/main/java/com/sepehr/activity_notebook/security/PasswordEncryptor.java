package com.sepehr.activity_notebook.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptor {

    private static final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER =
            new BCryptPasswordEncoder();

    public String encryptPassword(String password){
        return B_CRYPT_PASSWORD_ENCODER.encode(password);
    }

    public boolean matches(String password, String encodedPassword){
        return B_CRYPT_PASSWORD_ENCODER.matches(password, encodedPassword);
    }

}
