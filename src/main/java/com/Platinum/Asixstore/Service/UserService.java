package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.UserDto;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User display_userId(int userId) {
        return userRepo.findById(userId);
    }

    public User display_userEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public List<User> show_user() {
        return userRepo.findAll();
    }

    public void update_user(int userId, UserDto userDto) throws IOException {
        User user = userRepo.findById(userId);

        user.setNama(userDto.getNama());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAlamat(userDto.getAlamat());
        user.setNoTelepon(userDto.getNoTelepon());
        user.setKota(userDto.getKota());
        user.setImg(userDto.getImg().getBytes());

        User updateUser = userRepo.save(user);

    }


}
