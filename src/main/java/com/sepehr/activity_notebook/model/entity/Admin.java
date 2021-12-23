package com.sepehr.activity_notebook.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document("admins")
@EqualsAndHashCode(of = "userName", callSuper = false)
@Data
@NoArgsConstructor
public class Admin extends Person {

    @Id
    private String id;

    @Indexed(unique = true)
    private String userName;

    private String password;

    @CreatedDate
    private Date joinAt;

    private Set<Employee> employeeSet = new HashSet<>();

    public void addEmployee(Employee employee){
        employeeSet.add(employee);
    }
}
