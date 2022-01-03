package com.sepehr.activity_notebook.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public abstract class Person {

    @NonNull
    private String name;

    @NonNull
    private String lastName;

    private Date birthDay;

    private Gender gender;

}
