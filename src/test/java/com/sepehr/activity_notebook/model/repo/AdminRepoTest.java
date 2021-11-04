package com.sepehr.activity_notebook.model.repo;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.DuplicateEntityException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
class AdminRepoTest {

    @Autowired
    AdminRepo adminRepo;

    private static final String USER_NAME = "sepehrmsm1379@gmail.com";

    @Test
    void saveAdmin() throws DuplicateEntityException {
        Admin admin = new Admin(USER_NAME, "secret_password");
        admin = adminRepo.safeSave(admin);

        assertTrue(adminRepo.existsById(admin.getId()));
        assertTrue(adminRepo.existsByUserName("sepehrmsm1379@gmail.com"));

        try {
            adminRepo.safeSave(new Admin(USER_NAME, "12345678910"));
            fail();
        }catch (Exception exception){
            assertTrue(exception instanceof DuplicateEntityException);
        }
    }
}
