package com.sepehr.activity_notebook.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EmployeeSEQ")
    @SequenceGenerator(name = "EmployeeSEQ", initialValue = 100)
    private long id;

    public Employee(@NonNull String userName, @NonNull String password) {
        super(userName, password);
    }

}
