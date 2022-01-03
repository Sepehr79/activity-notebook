package com.sepehr.activity_notebook.model.service;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.repo.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepo adminRepo;

    /**
     * @throws org.springframework.dao.DuplicateKeyException when input has the same username.
     */
    public void save(Admin admin){
        adminRepo.save(admin);
    }

    public Optional<Admin> findAdminByUserName(String userName) {
        return adminRepo.findByUserName(userName);
    }

    public Optional<Admin> findById(String id) {
        return adminRepo.findById(id);
    }

    public void removeAdmin(Admin admin) {
        adminRepo.delete(admin);
    }

    public Optional<Admin> findByUserName(String userName) {
        return adminRepo.findByUserName(userName);
    }
}
