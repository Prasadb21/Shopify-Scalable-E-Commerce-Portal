package com.SpringBoot.Ecomm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Map<Long , Integer> productQuantity;

    private double totalAmount;



}
