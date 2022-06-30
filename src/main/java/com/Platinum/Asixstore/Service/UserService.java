package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.UserDto;
import com.Platinum.Asixstore.Entity.Role;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Repository.RoleRepo;
import com.Platinum.Asixstore.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
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
//        user.setEmail(userDto.getEmail());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAlamat(userDto.getAlamat());
        user.setNoTelepon(userDto.getNoTelepon());
        user.setKota(userDto.getKota());
        user.setImg(userDto.getImg().getBytes());
        userRepo.save(user);
    }

    public void update_role(int userId) throws IOException {
        User user = userRepo.findById(userId);
        List<Role> getRole = roleRepo.findByIdRole(2); //Seller
        user.setRoles(getRole);
        userRepo.save(user);

    }







}
