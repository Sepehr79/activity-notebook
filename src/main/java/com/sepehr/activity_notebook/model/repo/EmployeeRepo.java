package com.sepehr.activity_notebook.model.repo;

import com.sepehr.activity_notebook.model.entity.Employee;
import com.sepehr.activity_notebook.model.exception.DuplicateEntityException;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepo extends PersonRepo<Employee>, CrudRepository<Employee, Long> {

    @Override
    default Employee safeSave(Employee employee) throws DuplicateEntityException {
        if (existsByUserName(employee.getUserName()))
            throw new DuplicateEntityException("Duplicate employee by username");
        return save(employee);
    }

    Optional<Employee> findByUserName(String userName);
}
