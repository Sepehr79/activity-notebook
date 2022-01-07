package com.sepehr.activity_notebook.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
public class Employee extends Person {

    private String additionalData;

    private List<Activity> activities = new ArrayList<>();

    public void addActivity(Activity activity){
        activities.add(activity);
    }

}
