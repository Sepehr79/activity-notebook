package com.sepehr.activity_notebook.model.repo;

import com.sepehr.activity_notebook.model.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepo extends MongoRepository<Admin, String> {
}
