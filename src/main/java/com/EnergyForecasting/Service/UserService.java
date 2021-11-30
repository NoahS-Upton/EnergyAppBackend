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

@Service
public class UserService {
    private final UserRepo userRepo;


    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;

    }

    public void registerUser(User user) throws NoSuchAlgorithmException {
        boolean exists =userRepo.findByUsername(user.getUsername()).isPresent();
        boolean emExist= userRepo.findByEmail(user.getEmail()).isPresent();
        if (exists){
            throw new IllegalStateException("Username Taken");
        }
        if (emExist){
            throw new IllegalStateException("email Taken");
        }

        user.setRole(UserRole.GENERAL);

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

        user.setPassword(password);

        userRepo.save(user);
    }


}
