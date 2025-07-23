package com.SpringBoot.Ecomm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.Order;
import org.springframework.cache.interceptor.CacheAspectSupport;

import java.sql.SQLTransactionRollbackException;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "UserTable")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonIgnore // this means during serialization , this order values will not participate.
    private List<Orders> orders;




}
