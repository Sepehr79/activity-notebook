package com.sepehr.activity_notebook.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Activity {

    private String title;

    private String description;

    private int score;

}
