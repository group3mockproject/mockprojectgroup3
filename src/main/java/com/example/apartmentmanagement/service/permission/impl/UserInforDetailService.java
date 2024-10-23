package com.example.apartmentmanagement.service.permission.impl;

import com.example.apartmentmanagement.dto.UserInforUserDetails;
import com.example.apartmentmanagement.entities.permission.UserRole;
import com.example.apartmentmanagement.entities.permission.AppUser;
import com.example.apartmentmanagement.repository.permission.UserRepository;
import com.example.apartmentmanagement.repository.permission.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInforDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(email);
        List<UserRole> userRoles = userRoleRepository.findAllByAppUser(user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserInforUserDetails(user, userRoles);
    }
}
