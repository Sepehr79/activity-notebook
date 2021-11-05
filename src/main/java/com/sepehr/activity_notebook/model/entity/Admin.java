package com.sepehr.activity_notebook.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, of = "id")
@Entity
@NoArgsConstructor
@Data
@ToString(of = "id")
public class Admin extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AdminSEQ")
    @SequenceGenerator(name = "AdminSEQ", initialValue = 100)
    private long id;

    @OneToMany(mappedBy = "admin", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Activity> activities = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employeeSet = new HashSet<>();

    public Admin(@NonNull String userName, @NonNull String password) {
        super(userName, password);
    }

    public void addActivity(Activity activity){
        activity.setAdmin(this);
        activities.add(activity);
    }

    public void addEmployee(Employee employee){
        employee.getAdminSet().add(this);
        employeeSet.add(employee);
    }
}
