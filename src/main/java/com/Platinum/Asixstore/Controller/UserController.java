package com.Platinum.Asixstore.Controller;

import com.Platinum.Asixstore.Dto.UserDto;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Service.UserService;
import com.Platinum.Asixstore.Service.impl.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserLoginServiceImpl userLoginService;

//    @PostMapping("/registrasi")
//    public ResponseEntity<?> registrasi_user(@RequestBody UserDto userDto){
//        return new ResponseEntity<>(userService.registrasi_user(userDto),HttpStatus.CREATED);
//    }
    @GetMapping("/user/display")
    public ResponseEntity<?> display_user(){
        return new ResponseEntity<>(userService.show_user(),HttpStatus.ACCEPTED);
    }

    @PostMapping("/registrasi")
    public ResponseEntity<?> registration (@RequestBody User user ){
//        Map <String, String> map = new HashMap<>();
        User userLogin = userLoginService.findByUsername(user.getEmail());
        if (userLogin !=null){
//            map.put(user.getUsername(), "username already exist");
            return new ResponseEntity<>(userLogin, HttpStatus.BAD_REQUEST);
        }else {
            userLoginService.saveUser(user);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
