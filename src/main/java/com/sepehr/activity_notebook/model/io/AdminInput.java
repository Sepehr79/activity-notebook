package com.sepehr.activity_notebook.model.io;

import com.sepehr.activity_notebook.model.entity.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class AdminInput extends AdminIO {
    

    public Admin admin(){
        return Admin.builder()
                .name(getName())
                .lastName(getLastName())
                .birthDay(getBirthDay())
                .gender(getGender())
                .userName(getUserName())
                .password(getPassword())
                .build();
    }

}
