package com.sepehr.activity_notebook.model.repo;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.DuplicateEntityException;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepo extends PersonRepo<Admin>, CrudRepository<Admin, Long> {
    @Override
    default Admin safeSave(Admin admin) throws DuplicateEntityException{
        if (existsByUserName(admin.getUserName()))
            throw new DuplicateEntityException("Duplicate admin by username");
        return save(admin);
    }

    Optional<Admin> findByUserName(String userName);
}
