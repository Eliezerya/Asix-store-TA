package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Entity.User;

public interface UserLoginService {
    public User findByUsername(String username);
    public User saveUser(User user);

}
