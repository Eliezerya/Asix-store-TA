package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.BuyerDto;
import com.Platinum.Asixstore.Entity.User;

public interface UserLoginService {
    public User findByUsername(String username);
    public User saveUserBuyer(BuyerDto user);
    public User saveUserSeller(BuyerDto user);
}
