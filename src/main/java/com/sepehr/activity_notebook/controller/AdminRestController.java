package com.sepehr.activity_notebook.controller;

import com.sepehr.activity_notebook.controller.pojo.MessageEntity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.DuplicateUserNameException;
import com.sepehr.activity_notebook.model.exception.UserNotFoundException;
import com.sepehr.activity_notebook.model.input.AdminDocument;
import com.sepehr.activity_notebook.model.service.AdminService;
import lombok.RequiredArgsConstructor;
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


}
