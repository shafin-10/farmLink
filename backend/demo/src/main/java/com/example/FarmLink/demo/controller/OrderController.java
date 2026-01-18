package com.example.FarmLink.demo.controller;

import com.example.FarmLink.demo.dto.OrderDto;
import com.example.FarmLink.demo.dto.PlaceOrderRequestDto;
import com.example.FarmLink.demo.model.Order;
import com.example.FarmLink.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody PlaceOrderRequestDto placeOrderRequestDto, Authentication authentication){
        String email = authentication.getName();
        Order order = orderService.placeOrder(placeOrderRequestDto, email);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/my")
    public ResponseEntity<List<OrderDto>> getMyOrders(Authentication authentication){
        String email = authentication.getName();
        List<OrderDto> myOrders = orderService.getMyOrders(email);

        return ResponseEntity.status(HttpStatus.OK).body(myOrders);
    }

}
