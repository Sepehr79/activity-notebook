package com.sepehr.activity_notebook.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ConditionalOnProperty(value = "security.enable", havingValue = "true")
public class EnableSecurityConfiguration extends WebSecurityConfigurerAdapter {


}
