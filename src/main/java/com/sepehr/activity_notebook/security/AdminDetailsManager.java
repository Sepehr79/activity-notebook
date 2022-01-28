package com.sepehr.activity_notebook.security;

import com.sepehr.activity_notebook.model.entity.Admin;
import com.sepehr.activity_notebook.model.exception.DuplicateUserNameException;
import com.sepehr.activity_notebook.model.service.AdminService;
import com.sepehr.activity_notebook.security.entity.AdminDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class AdminDetailsManager implements UserDetailsManager {

    private final AdminService adminService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDetails user) {
        AdminDetails adminDetails = (AdminDetails) user;
        if (!adminService.existsByUserName(adminDetails.getUsername())) {
            adminService.save(adminDetails.getAdmin().toBuilder().password(passwordEncoder.encode(adminDetails.getPassword())).build());
            return;
        }
        throw new DuplicateUserNameException("Username already taken");
    }

    @Override
    public void updateUser(UserDetails user) {
        AdminDetails adminDetails = (AdminDetails) user;
        adminService.save(adminDetails.getAdmin().toBuilder().password(passwordEncoder.encode(adminDetails.getPassword())).build());
    }

    @Override
    public void deleteUser(String username) {
        adminService.removeAdmin(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // NOOP
    }

    @Override
    public boolean userExists(String username) {
        return adminService.existsByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminService.findByUserName(username);
        if (admin.isPresent()){
            return new AdminDetails(admin.get());
        }
        throw new UsernameNotFoundException("Incorrect username or password");
    }

    public List<Admin> findAll(){
        return adminService.findAll();
    }

    public void removeAll(){
        adminService.removeAllAdmins();
    }
}
