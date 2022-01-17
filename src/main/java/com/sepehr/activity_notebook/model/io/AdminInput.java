package com.sepehr.activity_notebook.model.io;

import com.sepehr.activity_notebook.model.entity.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class AdminInput extends AdminIO {

    private String password;

    public Admin admin(){
        return Admin.builder()
                .name(getName())
                .lastName(getLastName())
                .userName(getUserName())
                .password(getPassword())
                .employees(getEmployees())
                .birthDay(getBirthDay())
                .gender(getGender())
                .build();
    }

}
