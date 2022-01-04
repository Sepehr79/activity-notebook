package com.sepehr.activity_notebook.model.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Gender;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class AdminDocument {
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private Date birthDay;
    private Gender gender;

    @JsonIgnore
    public Admin admin(){
        return Admin.builder()
                .name(name)
                .lastName(lastName)
                .userName(userName)
                .password(password)
                .birthDay(birthDay)
                .gender(getGender())
                .build();
    }
}
