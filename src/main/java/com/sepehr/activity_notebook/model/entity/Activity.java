package com.sepehr.activity_notebook.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Activity {

    private String title;

    private String description;

    private int score;

}
