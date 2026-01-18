package com.example.FarmLink.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private int productId;
    private int quantity;
}
