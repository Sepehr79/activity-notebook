package com.sepehr.activity_notebook.model.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
public class Admin extends Person {

    @Id
    @SequenceGenerator(name = "Admin_SEQ", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Admin_SEQ")
    private long id;

    public Admin(@NonNull String userName, @NonNull String password) {
        super(userName, password);
    }

}
