package com.sepehr.activity_notebook.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public abstract class Person {

    @NotNull(message = "is required")
    private String name;

    @NotNull(message = "is required")
    private String lastName;

    private Date birthDay;

    private Gender gender;

}
