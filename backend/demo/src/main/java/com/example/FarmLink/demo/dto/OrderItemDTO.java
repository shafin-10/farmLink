package com.example.FarmLink.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private int productId;
    private String productName;
    private int quantity;
    private Double price;
}
