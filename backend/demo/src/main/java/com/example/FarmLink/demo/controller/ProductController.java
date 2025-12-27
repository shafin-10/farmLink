package com.example.FarmLink.demo.controller;

import com.example.FarmLink.demo.model.Product;
import com.example.FarmLink.demo.dto.ResponseDto;
import com.example.FarmLink.demo.repository.ProductRepo;
import com.example.FarmLink.demo.repository.UserRepo;
import com.example.FarmLink.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<ResponseDto> saveProduct(@RequestHeader("invocationFrom") String invocationFrom,
                                                   @Valid @RequestBody Product product, Authentication authentication){
        log.info(String.format("Header invocationFrom = %s", invocationFrom));
        String email = authentication.getName();
        productService.saveProduct(product, email);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode("200");
        responseDto.setStatusMsg("Product saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgSaved", "true")
                .body(responseDto);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable int productId, Authentication authentication) {

        ResponseDto responseDto = new ResponseDto();

        if (!productRepo.existsById(productId)) {
            responseDto.setStatusMsg("Product not found");
            responseDto.setStatusCode("404");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(responseDto);
        }

        String email = authentication.getName();
        productService.deleteProduct(productId, email);

        responseDto.setStatusMsg("Product deleted successfully");
        responseDto.setStatusCode("200");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }

    @PatchMapping("/archiveProduct")
    public ResponseEntity<ResponseDto> archiveProduct(@RequestBody Product productReq){
        ResponseDto responseDto = new ResponseDto();
        Optional<Product> product = productRepo.findById(productReq.getProductId());
        if(product.isPresent()){
            product.get().setStatus(ARCHIVED);
            productRepo.save(product.get());
        }
        else {
            responseDto.setStatusCode("400");
            responseDto.setStatusMsg("invalid product id recieved");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(responseDto);
        }
        responseDto.setStatusCode("200");
        responseDto.setStatusMsg("Product Archived Successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }
}
