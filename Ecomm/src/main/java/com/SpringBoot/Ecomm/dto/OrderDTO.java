package com.SpringBoot.Ecomm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;

    private double totalAmount;

    private String status;

    private Date orderDate;

    private String username;

    private String email;

    private List<OrderItemDTO> orderItems;  // kya kya order kiya user ne

    public OrderDTO(Long id, double totalAmount, String status, Date orderDate, List<OrderItemDTO> orderItems) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }
}
