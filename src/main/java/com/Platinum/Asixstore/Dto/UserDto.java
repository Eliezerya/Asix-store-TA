package com.Platinum.Asixstore.Dto;

import com.Platinum.Asixstore.Entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private int userId;
    private String nama;
    private String email;
    private String password;
    private String alamat;
    private long noTtelepon;
    private String kota;
    private List<Role> role;
}
