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
import java.util.Optional;

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
    public Admin getByUserName(@PathVariable String userName) {
        Optional<Admin> admin = adminService.findByUserName(userName);
        if (admin.isPresent()){
            return admin.get();
        }
        throw new UserNotFoundException(userName);
    }

    @PostMapping("/admins")
    public Admin saveAdmin(@RequestBody AdminDocument adminDocument) {
        if (adminService.existsByUserName(adminDocument.getUserName()))
            throw new DuplicateUserNameException(adminDocument.getUserName());
        return adminService.save(adminDocument.admin());
    }

    @PutMapping("/admins")
    public Admin updateAdmin(@RequestBody AdminDocument adminDocument) {
        Optional<Admin> admin = adminService.findByUserName(adminDocument.getUserName());
        if (admin.isPresent()){
            return adminService.save(
                    adminDocument.admin().toBuilder()
                            .id(admin.get().getId())
                            .joinAt(admin.get().getJoinAt())
                            .employees(admin.get().getEmployees())
                            .build()
            );
        }
        throw new UserNotFoundException(adminDocument.getUserName());
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
