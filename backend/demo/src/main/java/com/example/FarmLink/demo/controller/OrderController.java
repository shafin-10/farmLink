package com.example.FarmLink.demo.controller;

import com.example.FarmLink.demo.dto.OrderDto;
import com.example.FarmLink.demo.dto.PlaceOrderRequestDto;
import com.example.FarmLink.demo.dto.ResponseDto;
import com.example.FarmLink.demo.model.Order;
import com.example.FarmLink.demo.service.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderDetails(@PathVariable int orderId, Authentication authentication){
        String email = authentication.getName();
        OrderDto orderDto = orderService.getOrderDetails(orderId, email);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/cancel")
    public ResponseEntity<ResponseDto> cancelOrder(@RequestParam(value = "orderId") int id,
                                                   @RequestBody(required = false) String msg, Authentication authentication){
        String email = authentication.getName();
        ResponseDto responseDto = new ResponseDto();

        orderService.cancelOrder(email, id);
        responseDto.setStatusCode("200");
        responseDto.setStatusMsg("Order cancelled successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
