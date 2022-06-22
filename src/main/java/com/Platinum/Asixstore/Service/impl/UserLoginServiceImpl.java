package com.Platinum.Asixstore.Service.impl;

import com.Platinum.Asixstore.Dto.BuyerDto;
import com.Platinum.Asixstore.Dto.UserDto;
import com.Platinum.Asixstore.Entity.Role;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Repository.RoleRepo;
import com.Platinum.Asixstore.Repository.UserRepo;
import com.Platinum.Asixstore.Service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserLoginServiceImpl implements UserLoginService, UserDetailsService {
    @Autowired
    public UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    private final Logger logger = LogManager.getLogger(UserLoginServiceImpl.class);

    public User saveUserBuyer(BuyerDto buyerDto) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(buyerDto.getPassword()));
        //set role
        List<Role> getRole = roleRepo.findByIdRole(1); //buyer
        user.setRoles(getRole);
        user.setNama((buyerDto.getNama()));
        user.setEmail((buyerDto.getEmail()));
        return userRepo.save(user);
    }
    public User saveUserSeller(BuyerDto buyerDto) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(buyerDto.getPassword()));
        //set role
        List<Role> getRole = roleRepo.findByIdRole(2); //Seller
        user.setRoles(getRole);
        user.setNama((buyerDto.getNama()));
        user.setEmail((buyerDto.getEmail()));
        return userRepo.save(user);
    }

    public User findByUsername(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            logger.error("user not found");
        } else {
            logger.info(email + "found .!");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), authorities);
    }
}