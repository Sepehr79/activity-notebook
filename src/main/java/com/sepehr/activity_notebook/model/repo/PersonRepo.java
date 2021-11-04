package com.sepehr.activity_notebook.model.repo;

import com.sepehr.activity_notebook.model.entity.Person;
import com.sepehr.activity_notebook.model.exception.DuplicateEntityException;

public interface PersonRepo<T extends Person> {
    boolean existsByUserName(String userName);
    T safeSave(T input) throws DuplicateEntityException;
}
