package com.sepehr.activity_notebook.model.service;

import com.sepehr.activity_notebook.model.entity.Activity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Employee;
import com.sepehr.activity_notebook.model.exception.DuplicateEntityException;
import com.sepehr.activity_notebook.model.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmployeeServiceTest {

    private static final String USER_MAME = "sepehrmsm1379@gmail.com";
    private static final String PASSWORD = "secret123456789";

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AdminService adminService;

    @Test
    void testAddActivityToEmployee(){
        Activity activity1 = new Activity("act1", 3);
        Activity activity2 = new Activity("act2", 4);
        Activity activity3 = new Activity("act3", 5);

        Employee employee = new Employee(USER_MAME, PASSWORD);

        employee.addActivity(activity1);
        employee.addActivity(activity2);
        employee.addActivity(activity3);

        employeeService.saveEmployee(employee);

        Employee savedEmployee = employeeService.findEmployeeByUserName(USER_MAME);

        assertEquals(PASSWORD ,savedEmployee.getPassword());
        assertEquals(3 ,savedEmployee.getActivitySet().size());

    }

    @Test
    void testSaveActivityWithAdmin() throws DuplicateEntityException, EntityNotFoundException {
        Admin admin = new Admin(USER_MAME, PASSWORD);
        Employee employee = new Employee(USER_MAME + "different", PASSWORD);
        Activity activity = new Activity("simple", 1);

        admin.addActivity(activity);
        employee.addActivity(activity);

        adminService.saveAdmin(admin);
        employeeService.saveEmployee(employee);

        Admin savedAdmin = adminService.findAdminByUserName(USER_MAME);
        Employee savedEmployee = employeeService.findEmployeeByUserName(USER_MAME + "different");

        assertEquals(1, savedAdmin.getActivities().size());
        assertEquals(1, savedEmployee.getActivitySet().size());

        Activity employeeActivity = savedEmployee.getActivitySet().stream().findFirst()
                .orElseThrow(EntityNotFoundException::new);
        Activity adminActivity = savedAdmin.getActivities().stream().findFirst()
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(employeeActivity.getActivityName(), adminActivity.getActivityName()); // simple
    }

}
