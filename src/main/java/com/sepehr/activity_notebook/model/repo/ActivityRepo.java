package com.sepehr.activity_notebook.model.repo;

import com.sepehr.activity_notebook.model.entity.Activity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepo extends CrudRepository<Activity, Long> {
    List<Activity> findAllByAdminId(long id);
    List<Activity> findAllByAdminUserName(String userName);
}
