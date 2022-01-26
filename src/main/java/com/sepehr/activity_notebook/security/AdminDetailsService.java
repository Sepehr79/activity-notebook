package com.sepehr.activity_notebook.security;

import com.sepehr.activity_notebook.model.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final AdminService adminService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminService.findByUserName(username).orElseThrow(() -> new AuthenticationServiceException("Username or password is not correct"));
    }
}
