package com.sepehr.activity_notebook.model.repo;

import com.sepehr.activity_notebook.model.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class AdminRepoTest {

    @Autowired
    AdminRepo adminRepo;

    @Test
    void saveAdmin(){

        Admin admin = new Admin("sepehrmsm1379@gmail.com", "secret_password");
        admin.setName("sepehr");
        admin.setAge(20);
        admin.setLastName("mollaei");

        adminRepo.save(admin);

        assertTrue(adminRepo.existsById(admin.getId()));
        assertEquals(100, admin.getId());

        admin.setName("ali");
        adminRepo.save(admin); // Update admin

        assertEquals("ali" ,adminRepo.findById(100L).get().getName());

        assertEquals(1, adminRepo.count());
    }


}
