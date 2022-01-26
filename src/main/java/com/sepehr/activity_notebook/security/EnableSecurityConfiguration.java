package com.sepehr.activity_notebook.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@ConditionalOnProperty(value = "security.enable", havingValue = "true")
@RequiredArgsConstructor
public class EnableSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AdminDetailsService adminDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers( "/notebook/v1/admins/{userName}").access("@authorizationGuard.checkUserId(authentication, #userName)")
                .antMatchers(HttpMethod.GET, "/notebook/v1/admins").authenticated()
                .antMatchers(HttpMethod.DELETE).authenticated()
        .and()
                .formLogin(Customizer.withDefaults());
    }
}
