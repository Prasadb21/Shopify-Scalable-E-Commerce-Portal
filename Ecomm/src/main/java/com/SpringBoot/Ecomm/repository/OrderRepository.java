package com.SpringBoot.Ecomm.repository;

import com.SpringBoot.Ecomm.model.Orders;
import com.SpringBoot.Ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders , Long> {


    @Query("Select o from Orders o Join FETCH o.user ")
    List<Orders> findAllOrdersWithUsers();

    List<Orders>  findByUser(User user);


}
