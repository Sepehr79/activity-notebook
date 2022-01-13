package com.sepehr.activity_notebook.model.io;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@ToString(callSuper = true)
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AdminOutput extends AdminIO {

    private Date joinAt;

}
