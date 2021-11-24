package com.sepehr.activity_notebook.model.service;

import com.sepehr.activity_notebook.model.entity.Activity;
import com.sepehr.activity_notebook.model.entity.Employee;
import com.sepehr.activity_notebook.model.repo.ActivityRepo;
import com.sepehr.activity_notebook.model.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ActivityRepo activityRepo;


    public void saveEmployee(Employee employee){
        employeeRepo.save(employee);
    }

    public Employee findEmployeeByUserName(String userMame) {
        return employeeRepo.findByUserName(userMame)
                .orElseThrow(IllegalArgumentException::new);
    }
}
