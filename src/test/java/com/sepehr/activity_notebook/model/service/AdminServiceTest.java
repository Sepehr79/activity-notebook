package com.sepehr.activity_notebook.model.service;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.repo.AdminRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static com.sepehr.activity_notebook.model.entity.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(
        properties = {
                "spring.data.mongodb.database=test123",
                "spring.data.mongodb.auto-index-creation=true"
        }
)
@Slf4j
class AdminServiceTest {

    private static final String USER_NAME = "sepehrmsm1379@gmail.com";

    @Autowired
    AdminService adminService;

    @Autowired
    AdminRepo adminRepo;

    private static final Admin ADMIN = Admin.builder()
                    .userName(USER_NAME)
                    .password("1234")
                    .name("Sepehr")
                    .lastName("mollaei")
                    .gender(MALE)
                    .build();;

    @BeforeEach
    void saveAdmin(){
        adminService.save(ADMIN);
    }

    @AfterEach
    void clearMongo(){
        adminRepo.deleteAll();
    }

    @Test
    void testSaveAndGetAdmin(){
        Admin savedAdmin = adminService.findAdminByUserName(USER_NAME).get();
        assertEquals(ADMIN, savedAdmin);
        assertNotNull(savedAdmin.getJoinAt());
    }

    @Test
    void testSaveAndUpdateAdmin(){
        final String name = "ALI";
        Admin ali = ADMIN.toBuilder().name(name).build();
        adminService.save(ali); // Update admin
        assertEquals(adminService.findById(ADMIN.getId()).get().getName(), name);
        assertEquals(1 ,adminRepo.count());
    }

    @Test
    void testDeleteAdmin(){
        adminService.removeAdmin(ADMIN);
        Optional<Admin> adminOptional = adminService.findByUserName(USER_NAME);
        assertTrue(adminOptional.isEmpty());
    }

    @Test
    void testWithDuplicateUserName(){
        Admin admin = Admin.builder().name("sepehr").lastName("mollaei")
                .userName(USER_NAME) // Duplicate userName
                .build();
        try {
            adminService.save(admin);
            fail();
        } catch (Exception exception){
            assertTrue(exception instanceof org.springframework.dao.DuplicateKeyException);
        }
    }

}
