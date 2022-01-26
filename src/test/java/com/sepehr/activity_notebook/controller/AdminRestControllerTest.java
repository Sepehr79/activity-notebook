package com.sepehr.activity_notebook.controller;

import com.sepehr.activity_notebook.controller.pojo.MessageEntity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Gender;
import com.sepehr.activity_notebook.model.io.AdminInput;
import com.sepehr.activity_notebook.model.io.AdminOutput;
import com.sepehr.activity_notebook.model.repo.AdminRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Simple CRUD operations on API
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AdminRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    AdminRepo adminRepo;


    private String url;

    private static final AdminInput ADMIN = AdminInput.builder()
            .name("Sepehr")
            .lastName("Mollaei")
            .userName("sepehr79")
            .password("1234")
            .gender(Gender.MALE)
            .birthDay(new Date(2000, Calendar.SEPTEMBER, 12))
            .build();

    @PostConstruct
    void createUrl(){
        url = "http://localhost:" + port + "/notebook/v1/admins";
    }

    @BeforeEach
    void createAdmin(){
        testRestTemplate.postForEntity(url, ADMIN , Admin.class);
    }

    @AfterEach
    void deleteAdmins(){
        testRestTemplate.delete(url);
    }

    @Test
    void testGettingAdminByUserName(){
        AdminOutput adminOutput = testRestTemplate.getForObject(url + "/sepehr79", AdminOutput.class);
        assertEquals("Sepehr" ,adminOutput.getName());
    }

    @Test
    void testUpdateAdmin(){
        final String userName = "/sepehr79";

        AdminOutput adminOutput = testRestTemplate.getForObject(url + userName, AdminOutput.class);
        Date createDated = (Date) adminOutput.getJoinAt().clone();

        AdminInput adminInput = AdminInput.builder().name("Reza").lastName(adminOutput.getLastName()).userName("sepehr80").password("123").build();
        testRestTemplate.put(url + userName, adminInput);// Change admin name

        AdminOutput savedOutput = testRestTemplate.getForObject(url + userName, AdminOutput.class);

        assertEquals("Reza", savedOutput.getName());
        assertEquals(1, testRestTemplate.getForObject(url, List.class).size());
        assertEquals(createDated, savedOutput.getJoinAt());

        String password = adminRepo.findByUserName(savedOutput.getUserName())
                .orElseThrow(IllegalArgumentException::new).getPassword();
        assertEquals("123", password);

    }

    @Test
    void testPasswordEncryption(){
        final String userName = "sample";
        testRestTemplate.postForEntity(url, ADMIN.toBuilder().userName(userName).build(), Admin.class);
        Optional<Admin> admin = adminRepo.findByUserName(userName);
        if (admin.isEmpty())
            fail();
        assertEquals("1234", admin.get().getPassword());
    }

    @Test
    void testValidationFields(){
        AdminInput adminInput = AdminInput.builder().lastName("mollaei").userName("new").password("1234").build(); // Name is required
        ResponseEntity<MessageEntity> responseEntity = testRestTemplate.postForEntity(url, adminInput, MessageEntity.class);
        assertEquals(HttpStatus.BAD_REQUEST ,responseEntity.getStatusCode());
        assertEquals("name: is required", Objects.requireNonNull(responseEntity.getBody()).getDescription());
    }
}
