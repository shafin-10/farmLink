package com.example.FarmLink.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {
    private int orderId;
    private String status;
    private Double totalAmount;
    private List<OrderItemDTO> items;
}
