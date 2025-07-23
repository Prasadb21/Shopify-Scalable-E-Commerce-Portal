package com.SpringBoot.Ecomm.controller;

import com.SpringBoot.Ecomm.model.PaymentOrder;
import com.SpringBoot.Ecomm.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentPage")
public class PaymentController {

    // This controller can be used to handle payment-related requests
    // For example, you can add methods to initiate payments, check payment status, etc.
    // Currently, it serves as a placeholder for future payment-related functionalities.

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody PaymentOrder order) {
        try {
            String serviceOrder = paymentService.createOrder(order);
            return ResponseEntity.ok(serviceOrder);
        } catch (Exception e) {
            e.printStackTrace();  // 👈 Add this to log the exact error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating order: " + e.getMessage()); // 👈 include exception
        }
    }

    @PostMapping("/updateOrder")
    public ResponseEntity<String> updateOrderStatus(@RequestParam String paymentId , @RequestParam String orderId ,
                                                    @RequestParam String status)
    {

        paymentService.updateOrderStatus(paymentId , orderId , status);

        System.out.println("Email sent successfully !!!");
        return ResponseEntity.ok("Order Updated successfully and email sent!!!");

    }







}
