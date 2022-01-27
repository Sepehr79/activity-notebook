package com.sepehr.activity_notebook.controller.api;

import com.sepehr.activity_notebook.controller.pojo.MessageEntity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.DuplicateUserNameException;
import com.sepehr.activity_notebook.model.exception.UserNotFoundException;
import com.sepehr.activity_notebook.model.io.AdminInput;
import com.sepehr.activity_notebook.model.io.AdminOutput;
import com.sepehr.activity_notebook.model.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@RequestMapping("/notebook/v1")
@RestController
@RequiredArgsConstructor
public class AdminRestController {

    private final AdminService adminService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

        Admin admin = adminService.save(adminDocument.admin().toBuilder().password(bCryptPasswordEncoder.encode(adminDocument.getPassword())).build());
        return admin.adminOutput();
    }

    /**
     * @throws UserNotFoundException if no result found
     * @throws ConstraintViolationException if required fields was null
     * We can't change username because of mongo index
     */
    @PutMapping("/admins/{userName}")
    public AdminOutput updateAdmin(@RequestBody AdminInput adminInput, @PathVariable String userName) {
        Optional<Admin> admin = adminService.findByUserName(userName);
        if (admin.isPresent()){
            Admin updatingAdmin = adminInput.admin().toBuilder()
                    .id(admin.get().getId())
                    .userName(userName)
                    .joinAt(admin.get().getJoinAt())
                    .password(bCryptPasswordEncoder.encode(adminInput.getPassword()))
                    .build();
            return adminService.save(updatingAdmin)
                    .adminOutput();
        }
        throw new UserNotFoundException(userName);
    }

    @DeleteMapping("/admins/{userName}")
    public MessageEntity removeAdmin(@PathVariable String userName){
        adminService.removeAdmin(userName);
        return new MessageEntity("Process successfully completed.", "Employee removed with username: " + userName);
    }

}
