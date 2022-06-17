package com.Platinum.Asixstore.Service.impl;

import com.Platinum.Asixstore.Entity.User;
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

@Service
@RequiredArgsConstructor
@Transactional
public class UserLoginServiceImpl implements UserLoginService, UserDetailsService {
    @Autowired
    public UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final Logger logger = LogManager.getLogger(UserLoginServiceImpl.class);

    public User saveUser(User userLogin) {
        userLogin.setPassword(passwordEncoder.encode(userLogin.getPassword()));
        return userRepo.save(userLogin);
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