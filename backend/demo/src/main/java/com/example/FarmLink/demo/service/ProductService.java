package com.example.FarmLink.demo.service;

import com.example.FarmLink.demo.dto.ProductDto;
import com.example.FarmLink.demo.dto.UserDto;
import com.example.FarmLink.demo.model.Product;
import com.example.FarmLink.demo.model.Users;
import com.example.FarmLink.demo.repository.ProductRepo;
import com.example.FarmLink.demo.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Product> getMyProdcts(String email) {
        Users users = userRepo.findByEmail(email);
        return productRepo.findByFarmer(users.getUserId());
    }

    public boolean saveProduct(Product product, String email) {
        boolean isSaved = false;
        Users farmer = userRepo.findByEmail(email);
        product.setFarmer(farmer);
        Product product1 = productRepo.save(product);
        if (product1 != null && product1.getProductId() > 0)
            isSaved = true;
        return isSaved;
    }

    public void deleteProduct(int id, String email){
       Users user = userRepo.findByEmail(email);
       Optional<Product> product = productRepo.findById(id);
       if (user.equals(product.get().getFarmer())){
           productRepo.deleteById(id);
       }
       else throw new EntityNotFoundException("User cannot delete product");
    }

    public ProductDto getByID(int id) {
        ProductDto productDto = new ProductDto();
        UserDto farmer = new UserDto();

        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productDto.setId(product.getProductId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        productDto.setPrice(product.getPrice());
        productDto.setHarvestDate(product.getHarvestDate());
        productDto.setStatus(product.getStatus());
        productDto.setStock(product.getStock());

        farmer.setName(product.getFarmer().getName());
        farmer.setEmail(product.getFarmer().getEmail());
        farmer.setMobileNum(product.getFarmer().getMobileNumber());

        productDto.setFarmer(farmer);

        return productDto;
    }
}
