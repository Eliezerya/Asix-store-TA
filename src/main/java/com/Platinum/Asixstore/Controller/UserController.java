package com.Platinum.Asixstore.Controller;

import com.Platinum.Asixstore.Dto.BuyerDto;
import com.Platinum.Asixstore.Dto.UserDto;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Repository.UserRepo;
import com.Platinum.Asixstore.Service.UserService;
import com.Platinum.Asixstore.Service.impl.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@Transactional
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserLoginServiceImpl userLoginService;
    @Autowired
    UserRepo userRepo;

    public Authentication authentication(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }


    @GetMapping("/user/display") // view all user
    public ResponseEntity<?> display_user() {
        return new ResponseEntity<>(userService.show_user(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/user/update/{userId}") // update user
    public ResponseEntity<?> update_user(@PathVariable("userId") int userId, @RequestParam("img") MultipartFile fileUpload, UserDto userDto) throws IOException {
        User user = userLoginService.findByEmail(userDto.getEmail());
        User userToken = userRepo.findById(userId);
        if (user != null) {
            return new ResponseEntity<>("BLOK",HttpStatus.BAD_REQUEST);
        } else {
            if(userToken.getEmail().equalsIgnoreCase(authentication().getPrincipal().toString())){
                userDto.setImg(fileUpload);
                userService.update_user(userId, userDto);
                userService.display_userId(userId);

                return new ResponseEntity<>("Update Profile Berhasil",HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>("blok",HttpStatus.BAD_GATEWAY);
            }
        }
    }

    @PutMapping("/user/update-role/{userId}")
    public ResponseEntity<?> update_role(@PathVariable("userId") int userId, UserDto userDto) throws IOException {
        User user = userLoginService.findByEmail(userDto.getEmail());
        User userToken = userRepo.findById(userId);
        if (user != null) {
            return new ResponseEntity<>("BLOK",HttpStatus.BAD_REQUEST);
        } else {
            if(userToken.getEmail().equalsIgnoreCase(authentication().getPrincipal().toString())){
                userService.update_role(userId);
                userService.display_userId(userId);
                return new ResponseEntity<>("Update Seller telah berhasil,\nAnda bisa menjual barang",HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>("blok",HttpStatus.BAD_GATEWAY);
            }
        }
    }

    @GetMapping("/user/display/{email}") // view user by email
    public ResponseEntity<?> user_display_byEmail(@PathVariable String email) {
        if (userService.display_userEmail(email) != null){
            User users = userService.display_userEmail(email);
            return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("/Buyer/registrasi") //register
    public ResponseEntity<?> submit_user_buyer(@RequestBody BuyerDto buyerDto) {
        User userLogin = userLoginService.findByUsername(buyerDto.getEmail());
        if (userLogin != null) {
            return new ResponseEntity<>(userLogin, HttpStatus.BAD_REQUEST);
        } else {
            userLoginService.saveUserBuyer(buyerDto);
            return new ResponseEntity<>("Registrasi Berhasil",HttpStatus.CREATED);
        }
    }

}
