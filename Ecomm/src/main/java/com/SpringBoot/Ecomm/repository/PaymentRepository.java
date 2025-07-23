package com.SpringBoot.Ecomm.repository;

import com.SpringBoot.Ecomm.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentOrder, Long> {

    PaymentOrder findByOrderId(String orderId);

}
