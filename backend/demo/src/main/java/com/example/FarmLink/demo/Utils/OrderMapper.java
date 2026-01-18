package com.example.FarmLink.demo.Utils;

import com.example.FarmLink.demo.dto.OrderDto;
import com.example.FarmLink.demo.dto.OrderItemDTO;
import com.example.FarmLink.demo.model.Order;
import com.example.FarmLink.demo.model.OrderItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderMapper {
    public static OrderDto toDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setStatus(order.getStatus().name());
        orderDto.setTotalAmount(order.getTotalAmount());

        List<OrderItemDTO> itemDtos = new ArrayList<>();
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                OrderItemDTO i = new OrderItemDTO();
                i.setProductId(item.getProduct().getProductId());
                i.setProductName(item.getProduct().getName());
                i.setQuantity(item.getQuantity());
                i.setPrice(item.getPrice());

                itemDtos.add(i);
            }
        }
        orderDto.setItems(itemDtos);
        return orderDto;
    }
}
