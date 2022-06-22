package com.Platinum.Asixstore.Controller;

import com.Platinum.Asixstore.Dto.BuyerDto;
import com.Platinum.Asixstore.Dto.UserDto;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Service.UserService;
import com.Platinum.Asixstore.Service.impl.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserLoginServiceImpl userLoginService;

    @GetMapping("/user/display")
    public ResponseEntity<?> display_user(){
        return new ResponseEntity<>(userService.show_user(),HttpStatus.ACCEPTED);
    }

    @PutMapping("/user/update/{userId}")
    public ResponseEntity<?> update_user(@PathVariable("userId") int userId, @RequestParam("img") MultipartFile fileUpload, UserDto userDto) throws IOException
    {
        userDto.setImg(fileUpload);
        userService.update_user(userId,userDto);
        User response = userService.display_userId(userId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/Buyer/registrasi")
    public ResponseEntity<?> submit_user_buyer (@RequestBody BuyerDto buyerDto){
//        Map <String, String> map = new HashMap<>();
        User userLogin = userLoginService.findByUsername(buyerDto.getEmail());
        if (userLogin !=null){
//            map.put(user.getUsername(), "username already exist");
            return new ResponseEntity<>(userLogin, HttpStatus.BAD_REQUEST);
        }else {
            userLoginService.saveUserBuyer(buyerDto);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/Seller/registrasi")
    public ResponseEntity<?> submit_user_seller (@RequestBody BuyerDto buyerDto){
//        Map <String, String> map = new HashMap<>();
        User userLogin = userLoginService.findByUsername(buyerDto.getEmail());
        if (userLogin !=null){
//            map.put(user.getUsername(), "username already exist");
            return new ResponseEntity<>(userLogin, HttpStatus.BAD_REQUEST);
        }else {
            userLoginService.saveUserSeller(buyerDto);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
