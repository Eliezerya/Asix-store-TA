package com.Platinum.Asixstore.Controller;

import com.Platinum.Asixstore.Dto.UserDto;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/registrasi")
    public ResponseEntity<?> registrasi_user(@RequestBody UserDto userDto){

        return new ResponseEntity<>(userService.registrasi_user(userDto),HttpStatus.CREATED);
    }
    @GetMapping("/user/display")
    public ResponseEntity<?> display_user(){
        return new ResponseEntity<>(userService.show_user(),HttpStatus.ACCEPTED);
    }
}
