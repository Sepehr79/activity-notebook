package com.sepehr.activity_notebook.model.service;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.DuplicateUserNameException;
import com.sepehr.activity_notebook.model.repo.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepo adminRepo;

    public Admin save(Admin admin) throws DuplicateUserNameException {
        return adminRepo.save(admin);
    }

    public Optional<Admin> findById(String id) {
        return adminRepo.findById(id);
    }

    public void removeAdmin(Admin admin) {
        adminRepo.delete(admin);
    }

    public void removeAdmin(String userName){
        adminRepo.removeAdminByUserName(userName);
    }

    public void removeAllAdmins() { adminRepo.deleteAll(); }

    public Optional<Admin> findByUserName(String userName) {
        return adminRepo.findByUserName(userName);
    }

    public List<Admin> findAll(){
        return adminRepo.findAll();
    }

    public boolean existsByUserName(String userName){
        return adminRepo.existsByUserName(userName);
    }
}
