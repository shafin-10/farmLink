package com.example.FarmLink.demo.repository;

import com.example.FarmLink.demo.model.Roles;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {
    Roles getByRoleName(String roleName);
}
