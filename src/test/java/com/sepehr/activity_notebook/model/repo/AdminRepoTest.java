package com.sepehr.activity_notebook.model.repo;

import com.sepehr.activity_notebook.ActivityNotebookApplication;
import com.sepehr.activity_notebook.model.entity.Activity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Employee;
import com.sepehr.activity_notebook.model.entity.Gender;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ContextConfiguration(classes = ActivityNotebookApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public class AdminRepoTest {

    private static final String USERNAME = "sepehrmsm1379@gmail.com";

    @Autowired
    AdminRepo adminRepo;

    @After
    public void clearDataBase(){
        adminRepo.deleteAll();
    }

    @Test
    public void testSaveAdmin(){
        Admin admin = new Admin();
        admin.setName("sepehr");
        admin.setLastName("mollaei");
        admin.setUserName("sepehrmsm1379@gmail.com");
        admin.setPassword("1234");

        Employee employee = new Employee();
        employee.setName("sepehr");
        employee.setLastName("mollaei");
        employee.setGender(Gender.MALE);

        employee.addActivity(new Activity("test", "This is just a test", 10));

        admin.addEmployee(employee);

        adminRepo.insert(admin);

        Optional<Admin> savedAdmin = adminRepo.findByUserName(USERNAME);
        if (savedAdmin.isEmpty())
            fail();
        assertEquals("sepehr" ,savedAdmin.get().getName());
    }

}
