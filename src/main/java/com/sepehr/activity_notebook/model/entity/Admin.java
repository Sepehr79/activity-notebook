package com.sepehr.activity_notebook.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
public class Admin extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AdminSEQ")
    @SequenceGenerator(name = "AdminSEQ", initialValue = 100)
    private long id;

    public Admin(@NonNull String userName, @NonNull String password) {
        super(userName, password);
    }

}
