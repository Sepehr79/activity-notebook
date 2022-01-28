package com.sepehr.activity_notebook.controller.api;

import com.sepehr.activity_notebook.controller.pojo.MessageEntity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.UserNotFoundException;
import com.sepehr.activity_notebook.model.io.AdminInput;
import com.sepehr.activity_notebook.model.io.AdminOutput;
import com.sepehr.activity_notebook.security.AdminDetailsManager;
import com.sepehr.activity_notebook.security.entity.AdminDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RequestMapping("/notebook/v1")
@RestController
@RequiredArgsConstructor
public class AdminRestController {

    private final AdminDetailsManager adminDetailsManager;

    @GetMapping("/admins/{userName}")
    public AdminOutput getByUserName(@PathVariable String userName) {
        AdminDetails adminDetails = (AdminDetails) adminDetailsManager.loadUserByUsername(userName);
        return adminDetails.getAdmin().adminOutput();
    }

    @PostMapping("/admins")
    public AdminOutput saveAdmin(@RequestBody AdminInput adminDocument) {
        Admin admin = adminDocument.admin();
        adminDetailsManager.createUser(new AdminDetails(admin));
        return admin.adminOutput();
    }

    /**
     * @throws UserNotFoundException if no result found
     * @throws ConstraintViolationException if required fields was null
     * We can't change username because of mongo index
     */
    @PutMapping("/admins/{userName}")
    public AdminOutput updateAdmin(@RequestBody AdminInput adminInput, @PathVariable String userName) {
        AdminDetails adminDetails = (AdminDetails) adminDetailsManager.loadUserByUsername(userName);
        Admin updatingAdmin = adminInput.admin().toBuilder()
                .id(adminDetails.getAdmin().getId())
                .userName(adminDetails.getUsername())
                .joinAt(adminDetails.getAdmin().getJoinAt())
                .build();
        adminDetailsManager.updateUser(new AdminDetails(updatingAdmin));
        return updatingAdmin.adminOutput();
    }

    @DeleteMapping("/admins/{userName}")
    public MessageEntity removeAdmin(@PathVariable String userName){
        adminDetailsManager.deleteUser(userName);
        return new MessageEntity("Process successfully completed.", "Employee removed with username: " + userName);
    }

}
