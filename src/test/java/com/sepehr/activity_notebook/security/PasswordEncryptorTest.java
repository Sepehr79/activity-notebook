package com.sepehr.activity_notebook.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordEncryptorTest {

    private static final PasswordEncryptor PASSWORD_ENCRYPTOR = new PasswordEncryptor();

    @Test
    void testEncryptPassword(){
        final String password = "Hello world!";
        final String encrypted = PASSWORD_ENCRYPTOR.encryptPassword(password);
        assertTrue(PASSWORD_ENCRYPTOR.matches(password, encrypted));
        assertFalse(PASSWORD_ENCRYPTOR.matches("Wrong password", encrypted));
    }

}
