package com.example.FarmLink.demo.repository;

import com.example.FarmLink.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.buyer.userId = :buyerId ORDER BY o.createdAt DESC")
    List<Order> findByBuyerId(@Param("buyerId") int buyerId);
}
