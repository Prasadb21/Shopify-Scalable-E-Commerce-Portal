package com.SpringBoot.Ecomm.service;



import com.SpringBoot.Ecomm.model.User;
import com.SpringBoot.Ecomm.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {

        try
        {
            System.out.println("User Added to Database  !!!");
            return userRepository.save(user); // save is JPA implemented method
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;

    }


    public User loginUser(String email, String password ) {

        // check if user is there in DB or not
        User user = userRepository.findByEmail(email);

        if(user!=null && user.getPassword().equals(password))
        {
            return user;
        }
        else
        {
            return null;
        }

    }

    public List<User> getAllUsers() {

        return userRepository.findAll();

    }
}
