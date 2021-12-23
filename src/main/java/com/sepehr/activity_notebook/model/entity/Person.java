package com.sepehr.activity_notebook.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
public abstract class Person {

    @NonNull
    private String name;

    @NonNull
    private String lastName;

    private Date birthDay;

    private Gender gender;

}
