package com.sepehr.activity_notebook.model.repo;

import com.sepehr.activity_notebook.model.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class EmployeeRepoTest {

    @Autowired
    EmployeeRepo employeeRepo;

    @Test
    void saveEmployee(){
        Employee employee = new Employee("sepehrmsm1379@gmail.com", "secretpassword");
        employeeRepo.save(employee);

        assertTrue(employeeRepo.existsById(employee.getId()));
        assertTrue(employeeRepo.existsByUserName("sepehrmsm1379@gmail.com"));
    }

}
