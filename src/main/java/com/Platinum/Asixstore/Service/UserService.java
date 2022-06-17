package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.UserDto;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

//    public Boolean registrasi_user(UserDto userDto){
//        User user = new User();
//        user.setUserId(userDto.getUserId());
//        user.setNama(userDto.getNama());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setRoles(userDto.getRole());
//        userRepo.save(user);
//        return true;
//    }
    public List<User> show_user(){
        return userRepo.findAll();
    }


}
