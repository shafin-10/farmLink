package com.example.FarmLink.demo.service;

import com.example.FarmLink.demo.dto.UserDto;
import com.example.FarmLink.demo.model.Roles;
import com.example.FarmLink.demo.model.Users;
import com.example.FarmLink.demo.repository.RolesRepo;
import com.example.FarmLink.demo.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RolesRepo rolesRepo;

    public Users register(Users user) {
        try{
            Roles roles = rolesRepo.getByRoleName("USER");
            user.setRoles(roles);
        }catch (Exception e){
            throw new RuntimeException("Role not found");
        }

        userRepo.save(user);
        return user;
    }

    public List<Users> findAll() {
        return userRepo.findAll();
    }

    public Users findById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public Users getCurrentUser(String email) {
        Users currentUser = userRepo.findByEmail(email);
        return currentUser;
    }

    public Users updateProfile(UserDto userDto, String email) {
        Users users = getCurrentUser(email);

        users.setName(userDto.getName());
        users.setMobileNumber(userDto.getMobileNum());
        return userRepo.save(users);

    }
}
