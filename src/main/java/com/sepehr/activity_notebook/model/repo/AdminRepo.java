package com.sepehr.activity_notebook.model.repo;

import com.sepehr.activity_notebook.model.entity.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepo extends CrudRepository<Admin, Long> {
}
