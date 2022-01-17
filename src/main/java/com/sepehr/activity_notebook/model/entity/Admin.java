package com.sepehr.activity_notebook.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sepehr.activity_notebook.model.io.AdminOutput;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document("admins")
@EqualsAndHashCode(callSuper = true, of = "userName")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@Setter
public class Admin extends Person{

    @Indexed(unique = true)
    @NotNull(message = "is required")
    private String userName;

    @Id
    private String id;

    @NotNull(message = "is required")
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

    @JsonIgnore
    public AdminOutput adminOutput(){
        return AdminOutput.builder()
                .name(getName())
                .lastName(getLastName())
                .userName(userName)
                .employees(employees)
                .joinAt(joinAt)
                .gender(getGender())
                .birthDay(getBirthDay())
                .build();
    }

}
