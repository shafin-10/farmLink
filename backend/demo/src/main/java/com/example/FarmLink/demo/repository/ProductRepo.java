package com.example.FarmLink.demo.repository;

import com.example.FarmLink.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query(value = "SELECT * FROM products where products.status = :status", nativeQuery = true)
    List<Product> findByStatus(String status);


    @Query(value = "SELECT * FROM products WHERE products.farmer_id = :userId", nativeQuery = true)
    List<Product> findByFarmer(@Param("userId") int userId);
}
