package com.sepehr.activity_notebook.controller;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Gender;
import com.sepehr.activity_notebook.model.input.AdminDocument;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    private String url;

    private static final AdminDocument ADMIN = AdminDocument.builder()
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
        Admin admin = testRestTemplate.getForObject(url + "/sepehr79", Admin.class);
        assertEquals("Sepehr" ,admin.getName());
    }

}
