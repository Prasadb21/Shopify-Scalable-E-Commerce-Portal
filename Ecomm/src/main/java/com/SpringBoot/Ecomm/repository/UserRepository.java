package com.SpringBoot.Ecomm.repository;

import com.SpringBoot.Ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {


    User findByEmail(String email);


}
