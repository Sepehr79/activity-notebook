package com.sepehr.activity_notebook.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
public class Admin extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AdminSEQ")
    @SequenceGenerator(name = "AdminSEQ", initialValue = 100)
    private long id;

    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Activity> activities = new LinkedList<>();

    public Admin(@NonNull String userName, @NonNull String password) {
        super(userName, password);
    }

    public void addActivity(Activity activity){
        activity.setAdmin(this);
        activities.add(activity);
    }
}
