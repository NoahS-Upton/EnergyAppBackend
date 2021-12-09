package com.EnergyForecasting.Controllers;

import com.EnergyForecasting.Service.UserService;
import com.EnergyForecasting.Users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) throws NoSuchAlgorithmException {
        userService.registerUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/null")
    public ResponseEntity<User> getNullUser(){
        User user= new User();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
