package com.sepehr.activity_notebook.model.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "userName")
public abstract class Person {

    @Column(name = "user_name", nullable = false, unique = true)
    private @NonNull String userName;

    @Column(nullable = false)
    @Size(min = 8,message = "Password must have greater than 8 characters")
    private @NonNull String password;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private int age;

}
