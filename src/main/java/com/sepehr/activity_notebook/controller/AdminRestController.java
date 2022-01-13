package com.sepehr.activity_notebook.controller;

import com.sepehr.activity_notebook.controller.pojo.MessageEntity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.DuplicateUserNameException;
import com.sepehr.activity_notebook.model.exception.UserNotFoundException;
import com.sepehr.activity_notebook.model.io.AdminInput;
import com.sepehr.activity_notebook.model.io.AdminOutput;
import com.sepehr.activity_notebook.model.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/notebook/v1")
@RestController
@RequiredArgsConstructor
public class AdminRestController {

    private final AdminService adminService;

    @GetMapping("/admins")
    public List<AdminOutput> getAll(){
        return adminService.findAll().stream().map(Admin::adminOutput).collect(Collectors.toList());
    }

    @GetMapping("/admins/{userName}")
    public AdminOutput getByUserName(@PathVariable String userName) {
        Optional<Admin> admin = adminService.findByUserName(userName);
        if (admin.isPresent()){
            return admin.get().adminOutput();
        }
        throw new UserNotFoundException(userName);
    }

    @PostMapping("/admins")
    public AdminOutput saveAdmin(@RequestBody AdminInput adminDocument) {
        if (adminService.existsByUserName(adminDocument.getUserName()))
            throw new DuplicateUserNameException(adminDocument.getUserName());
        return adminService.save(adminDocument.admin()).adminOutput();
    }

    @PutMapping("/admins")
    public AdminOutput updateAdmin(@RequestBody AdminInput adminDocument) {
        Optional<Admin> admin = adminService.findByUserName(adminDocument.getUserName());
        if (admin.isPresent()){
            // Update admin all fields
            return adminService.save(
                    admin.get().toBuilder()
                            .name(adminDocument.getName())
                            .lastName(adminDocument.getLastName())
                            .birthDay(adminDocument.getBirthDay())
                            .password(adminDocument.getPassword())
                            .gender(adminDocument.getGender())
                            .employees(adminDocument.getEmployees())
                            .build()
            ).adminOutput();
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
