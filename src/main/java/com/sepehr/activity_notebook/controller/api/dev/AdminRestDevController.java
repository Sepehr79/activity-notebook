package com.sepehr.activity_notebook.controller.api.dev;

import com.sepehr.activity_notebook.controller.pojo.MessageEntity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.io.AdminOutput;
import com.sepehr.activity_notebook.model.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/notebook/v1")
@RestController
@RequiredArgsConstructor
@ConditionalOnProperty(value = "security.enable", havingValue = "false")
public class AdminRestDevController {

    private final AdminService adminService;

    @GetMapping("/admins")
    public List<AdminOutput> getAll(){
        return adminService.findAll().stream().map(Admin::adminOutput).collect(Collectors.toList());
    }


    @DeleteMapping("/admins")
    public MessageEntity removeAll(){
        adminService.removeAllAdmins();
        return new MessageEntity("Process successfully completed.", "All employees removed.");
    }

}