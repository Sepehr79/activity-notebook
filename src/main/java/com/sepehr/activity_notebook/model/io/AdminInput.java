package com.sepehr.activity_notebook.model.io;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class AdminInput extends AdminIO {

    private String password;

}
