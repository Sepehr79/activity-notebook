package com.sepehr.activity_notebook.model.service;

import com.sepehr.activity_notebook.model.entity.Activity;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.DuplicateEntityException;
import com.sepehr.activity_notebook.model.repo.ActivityRepo;
import com.sepehr.activity_notebook.model.repo.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepo adminRepo;

    private final ActivityRepo activityRepo;

    public void saveAdmin(Admin admin) throws DuplicateEntityException {
        adminRepo.safeSave(admin);
    }

    public Admin getAdminById(long id){
        return adminRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Activity> findActivitiesByAdminId(long id){
        return activityRepo.findAllByAdminId(id);
    }

    public List<Activity> findActivitiesByAdminUserName(String userName){
        return activityRepo.findAllByAdminUserName(userName);
    }

}
