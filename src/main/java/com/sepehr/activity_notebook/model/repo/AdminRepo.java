package com.sepehr.activity_notebook.model.repo;

import com.sepehr.activity_notebook.model.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepo extends MongoRepository<Admin, String> {

    Optional<Admin> findByUserName(String userName);

    List<Admin> findAll();

    void removeAdminByUserName(String userName);

    boolean existsByUserName(String userName);
}
