package com.sepehr.activity_notebook.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document("admins")
@EqualsAndHashCode(of = "userName", callSuper = false)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
public class Admin extends Person{

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
