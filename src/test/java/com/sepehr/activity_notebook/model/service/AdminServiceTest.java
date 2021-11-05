package com.sepehr.activity_notebook.model.service;

import com.sepehr.activity_notebook.model.entity.Activity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Employee;
import com.sepehr.activity_notebook.model.exception.DuplicateEntityException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminServiceTest {

    @Autowired
    AdminService adminService;

    private static final String USER_NAME = "sepehrmsm1379@gmail.com";
    private static final String PASSWORD = "123456789";

    @BeforeAll
    void saveAdmins() throws DuplicateEntityException {
        Admin admin = new Admin(USER_NAME, PASSWORD);

        admin.addActivity(new Activity("Interview", 10));
        admin.addActivity(new Activity("Exercise", 20));
        admin.addActivity(new Activity("Writing", 15));

        admin.addEmployee(new Employee("sa@gmail.com", "paraaaaaaaaaaa"));
        admin.addEmployee(new Employee("pa@gmail.com", "paraaaaaaaaaaa"));
        admin.addEmployee(new Employee("ka@gmail.com", "paraaaaaaaaaaa"));

        adminService.saveAdmin(admin);
    }

    @Test
    void adminTest(){

        Admin savedAdmin = adminService.findAdminByUserName(USER_NAME);
        assertNotNull(savedAdmin);
        assertEquals( 3 ,savedAdmin.getEmployeeSet().size());
        assertEquals(3 ,savedAdmin.getActivities().size());

        savedAdmin = adminService.findAdminById(100);
        assertEquals( 3 ,savedAdmin.getEmployeeSet().size());
        assertEquals(3 ,savedAdmin.getActivities().size());
    }

    @Test
    void activityTest(){
        List<Activity> activities = adminService.findActivitiesByAdminId(100);
        assertEquals(3, activities.size());

        List<Activity> activitiesByUserName = adminService.findActivitiesByAdminUserName(USER_NAME);
        assertEquals(3, activitiesByUserName.size());
    }

    @Test
    void employeeTest(){
        Set<Employee> employeeSet = adminService.findEmployeesByAdminId(100);
        assertEquals(3, employeeSet.size());
    }

}
