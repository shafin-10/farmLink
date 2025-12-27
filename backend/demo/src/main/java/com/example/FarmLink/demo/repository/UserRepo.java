package com.example.FarmLink.demo.repository;

import com.example.FarmLink.demo.model.UserPrincipal;
import com.example.FarmLink.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
}
