package com.example.FarmLink.demo.service;

import com.example.FarmLink.demo.model.UserPrincipal;
import com.example.FarmLink.demo.model.Users;
import com.example.FarmLink.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepo.findByEmail(email);
        if(users == null){
            throw new UsernameNotFoundException("User not foumd");
        }
        return new UserPrincipal(users);
    }
}
