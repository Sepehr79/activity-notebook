package com.sepehr.activity_notebook.controller;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Employee;
import com.sepehr.activity_notebook.model.exception.DuplicateUserNameException;
import com.sepehr.activity_notebook.model.exception.UserNotFoundException;
import com.sepehr.activity_notebook.model.input.AdminDocument;
import com.sepehr.activity_notebook.model.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notebook/v1")
@RestController
@RequiredArgsConstructor
public class AdminRestController {

    private final AdminService adminService;

    @GetMapping("/admins")
    public List<Admin> getAll(){
        return adminService.findAll();
    }

    @GetMapping("/admins/{userName}")
    public Admin getByUserName(@PathVariable String userName) throws UserNotFoundException {
        return adminService.findAdminByUserName(userName);
    }

    @PostMapping("/admins")
    public Admin saveAdmin(@RequestBody AdminDocument adminDocument) throws DuplicateUserNameException {
        return adminService.save(adminDocument.admin());
    }

    @PostMapping("/admins/{userName}/employees")
    public Admin addEmployees(@PathVariable String userName, @RequestBody List<Employee> employees) throws UserNotFoundException, DuplicateUserNameException {
        Admin admin = adminService.findAdminByUserName(userName);
        admin.addEmployees(employees);
        return adminService.save(admin);
    }

    @GetMapping("/admins/{userName}/employees")
    public List<Employee> employees(@PathVariable String userName) throws UserNotFoundException {
        Admin admin = adminService.findAdminByUserName(userName);
        return admin.getEmployees();
    }

    @GetMapping("/admins/{userName}/employees/{index}")
    public Employee getEmployee(@PathVariable String userName, @PathVariable String index) throws UserNotFoundException {
        Admin admin = adminService.findAdminByUserName(userName);
        return admin.getEmployees().get(Integer.parseInt(index));
    }

    @PutMapping("/admins")
    public Admin updateAdmin(@RequestBody AdminDocument adminDocument) throws UserNotFoundException, DuplicateUserNameException {
        Admin previousAdmin = adminService.findAdminByUserName(adminDocument.getUserName());
        return adminService.save(
            adminDocument.admin().toBuilder()
                    .id(previousAdmin.getId())
                    .joinAt(previousAdmin.getJoinAt())
                    .employees(previousAdmin.getEmployees())
                    .build()
        );
    }

    @DeleteMapping("/admins/{userName}")
    public MessageEntity removeAdmin(@PathVariable String userName){
        adminService.removeAdmin(userName);
        return new MessageEntity("Process successfully completed.", "Employee removed with username: " + userName);
    }

    @DeleteMapping("/admins")
    public MessageEntity removeAll(){
        adminService.removeAllAdmins();
        return new MessageEntity("Process successfully completed.", "All employees removed.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageEntity> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(
                new MessageEntity(userNotFoundException.getMessage(), ""),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateUserNameException.class)
    public ResponseEntity<MessageEntity> handleDuplicateUserNameException(DuplicateUserNameException duplicateUserNameException){
        return new ResponseEntity<>(
                new MessageEntity(duplicateUserNameException.getMessage(), ""),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<MessageEntity> handleIndexOutOfBoundsException(IndexOutOfBoundsException index){
        return new ResponseEntity<>(
                new MessageEntity(index.getMessage(), ""),
                HttpStatus.BAD_REQUEST
        );
    }

}
