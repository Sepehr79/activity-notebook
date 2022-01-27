package com.sepehr.activity_notebook.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@ConditionalOnProperty(value = "security.enable", havingValue = "true")
@RequiredArgsConstructor
public class EnableSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AdminDetailsService adminDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers( "/notebook/v1/admins/{userName}").access("@authorizationGuard.checkUserId(authentication, #userName)")
        .and()
                .formLogin(Customizer.withDefaults());
    }


}
