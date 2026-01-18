package com.example.FarmLink.demo.repository;

import com.example.FarmLink.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<Order, Integer> {
}
