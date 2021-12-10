package com.EnergyForecasting.Service;

import com.EnergyForecasting.Repository.UserRepo;
import com.EnergyForecasting.Users.User;
import com.EnergyForecasting.Users.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

//service for adding new users
@Service
public class UserService {
    private final UserRepo userRepo;

    //constructor
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //registers a new user
    public void registerUser(User user) throws NoSuchAlgorithmException {
        //checks that the user and email are not already in system
        boolean exists =userRepo.findByUsername(user.getUsername()).isPresent();
        boolean emExist= userRepo.findByEmail(user.getEmail()).isPresent();
        if (exists){
            throw new IllegalStateException("Username Taken");
        }
        if (emExist){
            throw new IllegalStateException("email Taken");
        }

        //sets user role, currently not important but could be used in future development stages
        user.setRole(UserRole.GENERAL);

        //encrypts password using SHA-512
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
        String password="";
        String hashed="";
        for (byte b:hashedPassword) {
            hashed=""+b;
            password+=hashed;
        }

        //sets users hashed password
        user.setPassword(password);

        //saves user
        userRepo.save(user);
    }


}
