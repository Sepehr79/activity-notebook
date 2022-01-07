package com.sepehr.activity_notebook.controller;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Employee;
import com.sepehr.activity_notebook.model.exception.UserNotFoundException;
import com.sepehr.activity_notebook.model.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/notebook/v1/admins")
@RestController
@RequiredArgsConstructor
public class EmployeeRestController {

    private final AdminService adminService;

    @GetMapping("/{userName}/employees")
    public List<Employee> employees(@PathVariable String userName) {
        Optional<Admin> admin = adminService.findByUserName(userName);
        if (admin.isPresent()){
            return admin.get().getEmployees();
        }
        throw new UserNotFoundException(userName);
    }

    @GetMapping("/{userName}/employees/{index}")
    public Employee getEmployee(@PathVariable String userName, @PathVariable String index) {
        Optional<Admin> admin = adminService.findByUserName(userName);
        if (admin.isPresent()){
            return admin.get().getEmployees().get(Integer.parseInt(index));
        }
        throw new UserNotFoundException(userName);
    }

    @PostMapping("/{userName}/employees")
    public Admin addEmployees(@PathVariable String userName, @RequestBody List<Employee> employees) {
        Optional<Admin> admin = adminService.findByUserName(userName);
        if (admin.isPresent()){
            admin.get().addEmployees(employees);
            return adminService.save(admin.get());
        }
        throw new UserNotFoundException(userName);
    }

    @PutMapping("/{userName}/employees")
    public Admin updateEmployees(@PathVariable String userName ,@RequestBody List<Employee> employees) {
        Optional<Admin> admin = adminService.findByUserName(userName);
        if (admin.isPresent()){
            admin.get().setEmployees(employees);
            return adminService.save(admin.get());
        }
        throw new UserNotFoundException(userName);
    }

    @DeleteMapping("/{userName}/employees")
    public Admin deleteEmployees(@PathVariable String userName)  {
        Optional<Admin> admin = adminService.findByUserName(userName);
        if (admin.isPresent()){
            admin.get().getEmployees().clear();
            return adminService.save(admin.get());
        }
        throw new UserNotFoundException(userName);
    }

    @DeleteMapping("/{userName}/employees/{index}")
    public Admin deleteEmployee(@PathVariable String userName, @PathVariable String index)  {
        Optional<Admin> admin = adminService.findByUserName(userName);
        if (admin.isPresent()){
            admin.get().getEmployees().remove(Integer.parseInt(index));
            return adminService.save(admin.get());
        }
        throw new UserNotFoundException(userName);
    }
}
