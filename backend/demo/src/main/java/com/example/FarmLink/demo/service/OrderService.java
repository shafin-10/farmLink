package com.example.FarmLink.demo.service;

import com.example.FarmLink.demo.Utils.OrderMapper;
import com.example.FarmLink.demo.dto.OrderDto;
import com.example.FarmLink.demo.dto.OrderItemRequest;
import com.example.FarmLink.demo.dto.PlaceOrderRequestDto;
import com.example.FarmLink.demo.model.Order;
import com.example.FarmLink.demo.model.OrderItem;
import com.example.FarmLink.demo.model.Product;
import com.example.FarmLink.demo.model.Users;
import com.example.FarmLink.demo.repository.OrderRepo;
import com.example.FarmLink.demo.repository.ProductRepo;
import com.example.FarmLink.demo.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public Order placeOrder(PlaceOrderRequestDto request, String buyerEmail){
        Users buyer = userRepo.findByEmail(buyerEmail);

        Order order = new Order();
        order.setBuyer(buyer);
        order.setStatus(Order.Status.CONFIRMED);

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for(OrderItemRequest itemRequest : request.getItems()){
            Product product = productRepo.findById(itemRequest.getProductId()).
                    orElseThrow(() -> new RuntimeException("Product not found"));

            if(product.getStock() < itemRequest.getQuantity()){
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - itemRequest.getQuantity());

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(product.getPrice());
            item.setOrder(order);

            total += product.getPrice() * itemRequest.getQuantity();
            orderItems.add(item);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);


        return orderRepo.save(order);
    }

    public List<OrderDto> getMyOrders(String email){
        Users user = userRepo.findByEmail(email);
        if (user == null)
            throw new RuntimeException("User not found");

        List<Order> orders = orderRepo.findByBuyerId(user.getUserId());

       List<OrderDto> orderDtos = new ArrayList<>();

       for(Order order : orders){
           orderDtos.add(OrderMapper.toDto(order));
       }

       return orderDtos;
    }


    public OrderDto getOrderDetails(int orderId, String email) {
        Users user = userRepo.findByEmail(email);
        if (user == null)
            throw new RuntimeException("user not found");

        Order order = orderRepo.findById(orderId).orElseThrow(() ->new RuntimeException("Order not found"));

        if ((order.getBuyer().getUserId() != user.getUserId())
            || user.getRoles().getRoleName().equals("ADMIN")){
                throw new RuntimeException("You are not authorized to view this order");
        }
        return OrderMapper.toDto(order);
    }

    @Transactional
    public void cancelOrder(String email, int orderId){
        Users user = userRepo.findByEmail(email);
        if (user == null)
            throw new RuntimeException("User not found");

        Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        if (!user.equals(order.getBuyer()))
            throw new RuntimeException("You do not own this product");

        if (order.getStatus() == Order.Status.DELIVERED ||
                order.getStatus() == Order.Status.SHIPPED||
                    order.getStatus() == Order.Status.CANCELLED){
            throw new RuntimeException("order cannot be cancelled");
        }

        for (OrderItem item : order.getItems()){
            Product product = item.getProduct();
            product.setStock(product.getStock() + item.getQuantity());
            productRepo.save(product);
        }

        order.setStatus(Order.Status.CANCELLED);
        orderRepo.save(order);
    }
}
