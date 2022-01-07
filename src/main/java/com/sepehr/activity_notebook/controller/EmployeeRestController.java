package com.sepehr.activity_notebook.controller;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Employee;
import com.sepehr.activity_notebook.model.exception.DuplicateUserNameException;
import com.sepehr.activity_notebook.model.exception.UserNotFoundException;
import com.sepehr.activity_notebook.model.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notebook/v1/admins")
@RestController
@RequiredArgsConstructor
public class EmployeeRestController {

    private final AdminService adminService;

    @GetMapping("/{userName}/employees")
    public List<Employee> employees(@PathVariable String userName) throws UserNotFoundException {
        Admin admin = adminService.findAdminByUserName(userName);
        return admin.getEmployees();
    }

    @GetMapping("/{userName}/employees/{index}")
    public Employee getEmployee(@PathVariable String userName, @PathVariable String index) throws UserNotFoundException {
        Admin admin = adminService.findAdminByUserName(userName);
        return admin.getEmployees().get(Integer.parseInt(index));
    }

    @PostMapping("/{userName}/employees")
    public Admin addEmployees(@PathVariable String userName, @RequestBody List<Employee> employees) throws UserNotFoundException, DuplicateUserNameException {
        Admin admin = adminService.findAdminByUserName(userName);
        admin.addEmployees(employees);
        return adminService.save(admin);
    }

    @PutMapping("/{userName}/employees")
    public Admin updateEmployees(@PathVariable String userName ,@RequestBody List<Employee> employees) throws UserNotFoundException, DuplicateUserNameException {
        Admin admin = adminService.findAdminByUserName(userName);
        admin.setEmployees(employees);
        return adminService.save(admin);
    }

    @DeleteMapping("/{userName}/employees")
    public Admin deleteEmployees(@PathVariable String userName) throws UserNotFoundException, DuplicateUserNameException {
        Admin adminByUserName = adminService.findAdminByUserName(userName);
        adminByUserName.getEmployees().clear();
        return adminService.save(adminByUserName);
    }

    @DeleteMapping("/{userName}/employees/{index}")
    public Admin deleteEmployee(@PathVariable String userName, @PathVariable String index) throws UserNotFoundException, DuplicateUserNameException {
        Admin adminByUserName = adminService.findAdminByUserName(userName);
        adminByUserName.getEmployees().remove(Integer.parseInt(index));
        return adminService.save(adminByUserName);
    }
}
