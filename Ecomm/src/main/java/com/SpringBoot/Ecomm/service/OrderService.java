package com.SpringBoot.Ecomm.service;

import com.SpringBoot.Ecomm.dto.OrderDTO;
import com.SpringBoot.Ecomm.dto.OrderItemDTO;
import com.SpringBoot.Ecomm.model.OrderItem;
import com.SpringBoot.Ecomm.model.Orders;
import com.SpringBoot.Ecomm.model.Product;
import com.SpringBoot.Ecomm.model.User;
import com.SpringBoot.Ecomm.repository.OrderRepository;
import com.SpringBoot.Ecomm.repository.ProductRepository;
import com.SpringBoot.Ecomm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantity, double totalAmount)
    {

        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found "));

        Orders orders = new Orders();
        orders.setUser(user);
        orders.setOrderDate(new Date());
        orders.setStatus("Pending !");
        orders.setTotalAmount(totalAmount);

        // User only comes ones, and we loop orderitem and product to add to list while billing.

        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();

        for(Map.Entry<Long , Integer> entry:productQuantity.entrySet())
        {
            Product product = productRepository.findById(entry.getKey()).
                    orElseThrow(()->new RuntimeException("Product Not Found !!"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(orders);
            orderItem.setProduct(product);
            orderItem.setQuantity(entry.getValue());


            orderItems.add(orderItem); // orderItems list is updated now with this current item.
            orderItemDTOS.add(new OrderItemDTO(product.getName() , product.getProductPrice() , entry.getValue()));


        }

        orders.setOrderItems(orderItems);
        Orders saveOrder = orderRepository.save(orders);

        return new OrderDTO(saveOrder.getId() , saveOrder.getTotalAmount() , saveOrder.getStatus(),
                            saveOrder.getOrderDate(), orderItemDTOS );

    }


    public List<OrderDTO> getAllOrders() {

        List<Orders> ordersWithUsers = orderRepository.findAllOrdersWithUsers();

        return ordersWithUsers.stream().map(this::convertToDTO).collect(Collectors.toList());

        

    }

    private OrderDTO convertToDTO(Orders orders) {

        List<OrderItemDTO> OrderItems = orders.getOrderItems().stream()
                .map(orderItem -> new OrderItemDTO(

                        orderItem.getProduct().getName(),
                        orderItem.getProduct().getProductPrice(),
                        orderItem.getQuantity())).collect(Collectors.toList());

        return new OrderDTO(
                orders.getId(),
                orders.getTotalAmount(),
                orders.getStatus(),
                orders.getOrderDate(),
                orders.getUser() != null ? orders.getUser().getName() : "UnKnown",
                orders.getUser()!=null ? orders.getUser().getEmail() : "Unknown",
                OrderItems
        );
    }

    public List<OrderDTO> getOrderByUser(Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty())
        {
            throw new RuntimeException("user not found ");
        }
        User user = userOptional.get();
        List<Orders> ordersList = orderRepository.findByUser(user);
        return ordersList.stream().map(this::convertToDTO).collect(Collectors.toList());


    }
}

