package com.example.FarmLink.demo.controller;

import com.example.FarmLink.demo.model.Users;
import com.example.FarmLink.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(){
        return "hello";
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user){
      Users savedUser = userService.register(user);
       return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }


}
