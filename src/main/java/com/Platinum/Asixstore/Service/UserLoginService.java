package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.BuyerDto;
import com.Platinum.Asixstore.Entity.User;

public interface UserLoginService {
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User saveUserBuyer(BuyerDto user);

}
