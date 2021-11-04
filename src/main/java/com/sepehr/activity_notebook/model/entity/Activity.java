package com.sepehr.activity_notebook.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ActSEQ")
    @SequenceGenerator(name = "ActSEQ", initialValue = 100)
    private long id;

    @Column(name = "activity")
    private @NonNull String activityName;

    private @NonNull int score;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REFRESH})
    @JoinColumn(name = "admin")
    private Admin admin;

}
