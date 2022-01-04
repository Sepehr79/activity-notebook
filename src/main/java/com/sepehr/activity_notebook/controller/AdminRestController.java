package com.sepehr.activity_notebook.controller;

import com.sepehr.activity_notebook.model.entity.Admin;
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

    @PutMapping("/admins")
    public Admin updateAdmin(Admin admin) throws UserNotFoundException, DuplicateUserNameException {
        Admin previousAdmin = adminService.findAdminByUserName(admin.getUserName());
        previousAdmin.toBuilder()
                .name(admin.getName())
                .lastName(admin.getLastName())
                .password(admin.getPassword())
                .birthDay(admin.getBirthDay())
                .gender(admin.getGender())
                .build();
        return adminService.save(previousAdmin);
    }

    @DeleteMapping("/admins/{userName}")
    public MessageEntity removeAdmin(@PathVariable String userName){
        adminService.removeAdmin(userName);
        return new MessageEntity("Process successfully completed.", "");
    }

    @DeleteMapping("/admins/sure")
    public MessageEntity removeAll(){
        adminService.removeAllAdmins();
        return new MessageEntity("Process successfully completed.", "");
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

}
