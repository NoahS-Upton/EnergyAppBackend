//package com.EnergyForecasting.Service;
//
//import com.EnergyForecasting.Repository.UserRepo;
//import com.EnergyForecasting.Users.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//public class UserService implements UserDetailsService {
//    private final UserRepo userRepo;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //private final TokenService tokenService;

//    @Autowired
//    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userRepo = userRepo;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepo.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User "+ username+" not found"));
//    }
//
//    public String registerUser(User user){
//        boolean exists =userRepo.findByUsername(user.getUsername()).isPresent();
//        boolean emExist= userRepo.findByEmail(user.getEmail()).isPresent();
//        if (exists){
//            throw new IllegalStateException("Username Taken");
//        }
//        if (emExist){
//            throw new IllegalStateException("email Taken");
//        }
//        String encPass= bCryptPasswordEncoder.encode(user.getPassword());
//        user.setPassword(encPass);
//        userRepo.save(user);
//        String tokenID=UUID.randomUUID().toString();
////        Token token = new Token(tokenID, LocalDateTime.now(),LocalDateTime.now().plusMinutes(10),user);
////        tokenService.saveToken(token);
//
//        return tokenID;
//    }
//
//    public int enableUser(String email) {
//        return userRepo.enableUser(email);
//    }
//}
