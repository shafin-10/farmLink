package com.example.FarmLink.demo.controller;

import com.example.FarmLink.demo.model.Product;
import com.example.FarmLink.demo.model.Response;
import com.example.FarmLink.demo.model.Users;
import com.example.FarmLink.demo.repository.ProductRepo;
import com.example.FarmLink.demo.repository.UserRepo;
import com.example.FarmLink.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.FarmLink.demo.model.Product.Status.ARCHIVED;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    @GetMapping("/getProductsByStatus")
    public List<Product> getProductsByStatus(@RequestParam(name = "status") String status){
        return productRepo.findByStatus(status);
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<Response> saveProduct(@RequestHeader("invocationFrom") String invocationFrom,
                                                @Valid @RequestBody Product product, Authentication authentication){
        log.info(String.format("Header invocationFrom = %s", invocationFrom));
        String email = authentication.getName();
        productService.saveProduct(product, email);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Product saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgSaved", "true")
                .body(response);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<Response> deleteProduct(@PathVariable int productId, Authentication authentication) {

        Response response = new Response();

        if (!productRepo.existsById(productId)) {
            response.setStatusMsg("Product not found");
            response.setStatusCode("404");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }

        String email = authentication.getName();
        productService.deleteProduct(productId, email);

        response.setStatusMsg("Product deleted successfully");
        response.setStatusCode("200");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/archiveProduct")
    public ResponseEntity<Response> archiveProduct(@RequestBody Product productReq){
        Response response = new Response();
        Optional<Product> product = productRepo.findById(productReq.getProductId());
        if(product.isPresent()){
            product.get().setStatus(ARCHIVED);
            productRepo.save(product.get());
        }
        else {
            response.setStatusCode("400");
            response.setStatusMsg("invalid product id recieved");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        response.setStatusCode("200");
        response.setStatusMsg("Product Archived Successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
