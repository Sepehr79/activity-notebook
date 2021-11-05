package com.sepehr.activity_notebook.model.service;

import com.sepehr.activity_notebook.model.entity.Activity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Employee;
import com.sepehr.activity_notebook.model.exception.DuplicateEntityException;
import com.sepehr.activity_notebook.model.repo.ActivityRepo;
import com.sepehr.activity_notebook.model.repo.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepo adminRepo;

    private final ActivityRepo activityRepo;

    public void saveAdmin(Admin admin) throws DuplicateEntityException {
        adminRepo.safeSave(admin);
    }

    public Admin findAdminById(long id){
        return adminRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Activity> findActivitiesByAdminId(long id){
        return activityRepo.findAllByAdminId(id);
    }

    public List<Activity> findActivitiesByAdminUserName(String userName){
        return activityRepo.findAllByAdminUserName(userName);
    }

    public Admin findAdminByUserName(String userName){
        return adminRepo.findByUserName(userName)
                .orElseThrow(IllegalArgumentException::new);
    }

    public Set<Employee> findEmployeesByAdminId(long i) {
        Optional<Admin> admin = adminRepo.findById(i);
        return admin.orElseThrow(IllegalArgumentException::new)
                .getEmployeeSet();
    }

}
