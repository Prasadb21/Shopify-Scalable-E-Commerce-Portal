package com.SpringBoot.Ecomm.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JsonBackReference // user will handle its reference.
    private User user;

    private double totalAmount;

    private String status;

    private Date orderDate;

    @OneToMany(mappedBy = "orders" , cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;



}
