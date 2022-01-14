package com.sepehr.activity_notebook.controller;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Gender;
import com.sepehr.activity_notebook.model.io.AdminIO;
import com.sepehr.activity_notebook.model.io.AdminInput;
import com.sepehr.activity_notebook.model.io.AdminOutput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Simple CRUD operations on API
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

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

        AdminInput adminInput = AdminInput.builder().name("Reza").lastName(adminOutput.getLastName()).userName("sepehr79").password("1234").build();
        testRestTemplate.put(url, adminInput);// Change admin name

        AdminOutput savedOutput = testRestTemplate.getForObject(url + userName, AdminOutput.class);

        assertEquals("Reza", savedOutput.getName());
        assertEquals(1, testRestTemplate.getForObject(url, List.class).size());
        assertEquals(createDated, savedOutput.getJoinAt());
    }
}
