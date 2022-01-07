package com.sepehr.activity_notebook.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document("admins")
@EqualsAndHashCode(callSuper = true, of = "userName")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@Setter
public class Admin extends Person{

    @Indexed(unique = true)
    @NonNull
    private String userName;

    @Id
    private String id;

    @NonNull
    @JsonIgnore
    private String password;

    @CreatedDate
    private Date joinAt;

    @JsonProperty("employees")
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void addEmployees(List<Employee> employees){
        this.employees.addAll(employees);
    }

}
