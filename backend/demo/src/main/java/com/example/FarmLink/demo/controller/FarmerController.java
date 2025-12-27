package com.example.FarmLink.demo.controller;

import com.example.FarmLink.demo.model.Product;
import com.example.FarmLink.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/farmer")
public class FarmerController {

    @Autowired
    private ProductService productService;

    @GetMapping("/myProducts")
    public ResponseEntity<List<Product>> getMyProducts(Authentication authentication){
        String email = authentication.getName();
        List<Product> products = productService.getMyProdcts(email);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
