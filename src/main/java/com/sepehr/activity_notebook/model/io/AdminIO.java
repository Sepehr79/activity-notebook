package com.sepehr.activity_notebook.model.io;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sepehr.activity_notebook.model.entity.Employee;
import com.sepehr.activity_notebook.model.entity.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@ToString
public abstract class AdminIO {

    private String name;

    private String lastName;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date birthDay;

    private Gender gender;

    private String userName;

    @JsonProperty("employees")
    private List<Employee> employees;


}
