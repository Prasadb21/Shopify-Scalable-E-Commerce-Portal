package com.SpringBoot.Ecomm.controller;

import com.SpringBoot.Ecomm.dto.OrderDTO;
import com.SpringBoot.Ecomm.model.OrderRequest;
import com.SpringBoot.Ecomm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500"})
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{userId}")
    public OrderDTO placeOrder(@PathVariable Long userId , @RequestBody OrderRequest orderRequest)
    {
        return orderService.placeOrder(userId , orderRequest.getProductQuantity() , orderRequest.getTotalAmount());

    }

    @GetMapping("/allOrders")
    public List<OrderDTO> getAllOrders()
    {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<OrderDTO> getOrderByUser(@PathVariable Long userId)
    {
        return orderService.getOrderByUser(userId);
    }

    

}
