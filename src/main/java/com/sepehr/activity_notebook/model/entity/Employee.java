package com.sepehr.activity_notebook.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, of = "id")
@Entity
@NoArgsConstructor
@Data
public class Employee extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EmployeeSEQ")
    @SequenceGenerator(name = "EmployeeSEQ", initialValue = 100)
    private long id;

    @ManyToMany(mappedBy = "employeeSet", fetch = FetchType.EAGER,
    cascade = {
            CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST
    })
    private Set<Admin> adminSet = new HashSet<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Activity> activitySet = new HashSet<>();

    public Employee(@NonNull String userName, @NonNull String password) {
        super(userName, password);
    }

    public void addAdmin(Admin admin){
        admin.getEmployeeSet().add(this);
        adminSet.add(admin);
    }

    public void addActivity(Activity activity){
        activity.setEmployee(this);
        activitySet.add(activity);
    }

}
