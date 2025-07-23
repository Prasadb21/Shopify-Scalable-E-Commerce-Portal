package com.SpringBoot.Ecomm.repository;

import com.SpringBoot.Ecomm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {



}
