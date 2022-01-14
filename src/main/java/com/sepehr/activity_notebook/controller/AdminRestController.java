package com.sepehr.activity_notebook.controller;

import com.sepehr.activity_notebook.controller.pojo.MessageEntity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.DuplicateUserNameException;
import com.sepehr.activity_notebook.model.exception.UserNotFoundException;
import com.sepehr.activity_notebook.model.io.AdminIO;
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
        return adminService.findAll().stream().map(AdminIO::fromAdmin).collect(Collectors.toList());
    }

    @GetMapping("/admins/{userName}")
    public AdminOutput getByUserName(@PathVariable String userName) {
        Optional<Admin> admin = adminService.findByUserName(userName);
        if (admin.isPresent()){
            return AdminIO.fromAdmin(admin.get());
        }
        throw new UserNotFoundException(userName);
    }

    @PostMapping("/admins")
    public AdminOutput saveAdmin(@RequestBody AdminInput adminDocument) {
        if (adminService.existsByUserName(adminDocument.getUserName()))
            throw new DuplicateUserNameException(adminDocument.getUserName());
        Admin admin = adminService.save(AdminIO.fromAdminInput(adminDocument));
        return AdminIO.fromAdmin(admin);
    }

    /**
     * Changing id & joinAt fields are impossible
     * @throws UserNotFoundException if no result found
     * @throws NullPointerException if username, password, name or last name was null
     */
    @PutMapping("/admins")
    public AdminOutput updateAdmin(@RequestBody AdminInput adminInput) {
        Optional<Admin> admin = adminService.findByUserName(adminInput.getUserName());
        if (admin.isPresent()){
            Admin updatingAdmin = AdminIO.fromAdminInput(adminInput);
            return AdminIO.fromAdmin(adminService.save(updatingAdmin.toBuilder().id(admin.get().getId()).joinAt(admin.get().getJoinAt()).build()));
        }
        throw new UserNotFoundException(adminInput.getUserName());
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
