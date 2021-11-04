package com.sepehr.activity_notebook.model.service;

import com.sepehr.activity_notebook.model.entity.Activity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.DuplicateEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    AdminService adminService;

    private static final String USER_NAME = "sepehrmsm1379@gmail.com";
    private static final String PASSWORD = "123456789";

    @BeforeEach
    void saveAdmins() throws DuplicateEntityException {
        Admin admin = new Admin(USER_NAME, PASSWORD);
        admin.addActivity(new Activity("Interview", 10));
        admin.addActivity(new Activity("Exercise", 20));
        admin.addActivity(new Activity("Writing", 15));

        adminService.saveAdmin(admin);
    }

    @Test
    void dataSourceTest(){
        Admin savedAdmin = adminService.getAdminById(100);
        assertNotNull(savedAdmin);
        assertEquals(3, savedAdmin.getActivities().size());

        List<Activity> activities = adminService.findActivitiesByAdminId(100);
        assertEquals(3, activities.size());

        List<Activity> activitiesByUserName = adminService.findActivitiesByAdminUserName(USER_NAME);
        assertEquals(3, activitiesByUserName.size());
    }

}
