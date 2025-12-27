package com.example.FarmLink.demo.controller;

import com.example.FarmLink.demo.dto.UserDto;
import com.example.FarmLink.demo.model.Response;
import com.example.FarmLink.demo.model.Users;
import com.example.FarmLink.demo.repository.UserRepo;
import com.example.FarmLink.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> usersList = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(usersList);
    }

    @GetMapping("/findById")
    public ResponseEntity<Users> findUserById(@RequestParam(name = "Id") int id){
        Users user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/ownProfile")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication){
        String email = authentication.getName();
        Users currentUser = userService.getCurrentUser(email);

        UserDto userDto = new UserDto();
        userDto.setName(currentUser.getName());
        userDto.setMobileNum(currentUser.getMobileNumber());
        userDto.setEmail(currentUser.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PatchMapping("updateProfile")
    public ResponseEntity<UserDto> updateProfile(@RequestBody UserDto userDto, Authentication authentication){
        String email = authentication.getName();
        Users updatedUser = userService.updateProfile(userDto, email);
        UserDto userDto1 = new UserDto();

        userDto1.setName(updatedUser.getName());
        userDto1.setMobileNum(updatedUser.getMobileNumber());

        return ResponseEntity.status(HttpStatus.OK).body(userDto1);

    }
}
