package com.sepehr.activity_notebook.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public abstract class Person {

    @Column(name = "user_name",unique = true, nullable = false)
    private @NonNull String userName;

    @Column(nullable = false)
    private @NonNull String password;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private int age;

}
