package com.SpringBoot.Ecomm.service;

import com.SpringBoot.Ecomm.model.Product;
import com.SpringBoot.Ecomm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {

        return productRepository.findAll();

    }


    public Product getProductById(Long productId) {

        return productRepository.findById(productId).orElse(null);

    }

    public Product addProduct(Product product) {

        return productRepository.save(product);

    }

    public void deleteProductById(Long id) {

        productRepository.deleteById(id);
        System.out.println("Product deleted successfully !!! ");

    }
}
