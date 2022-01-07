package com.sepehr.activity_notebook.model.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AdminDocument {
    
    private String name;
    
    private String lastName;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date birthDay;
    
    private Gender gender;
    
    private String userName;
    
    private String password;

    public Admin admin(){
        return Admin.builder()
                .name(name)
                .lastName(lastName)
                .birthDay(birthDay)
                .gender(gender)
                .userName(userName)
                .password(password)
                .build();
    }

}
