package com.example.FarmLink.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceOrderRequestDto {
    private List<OrderItemRequest> items;
}
