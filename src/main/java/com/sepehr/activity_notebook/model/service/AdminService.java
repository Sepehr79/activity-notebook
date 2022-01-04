package com.sepehr.activity_notebook.model.service;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.DuplicateUserNameException;
import com.sepehr.activity_notebook.model.exception.UserNotFoundException;
import com.sepehr.activity_notebook.model.repo.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepo adminRepo;

    /**
     * @throws DuplicateUserNameException when input has the same username.
     */
    public Admin save(Admin admin) throws DuplicateUserNameException {
        try {
            return adminRepo.save(admin);
        }catch (org.springframework.dao.DuplicateKeyException duplicateKeyException){
            throw new DuplicateUserNameException("Username already exists.", admin.getUserName());
        }
    }

    public Admin findAdminByUserName(String userName) throws UserNotFoundException {
        return adminRepo.findByUserName(userName).orElseThrow(() -> new UserNotFoundException(userName));
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
}
