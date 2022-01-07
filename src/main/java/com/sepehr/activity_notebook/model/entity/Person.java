package com.sepehr.activity_notebook.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public abstract class Person {

    @NonNull
    private String name;

    @NonNull
    private String lastName;

    private Date birthDay;

    private Gender gender;



}
