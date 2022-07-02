package com.Platinum.Asixstore.Dto;

import com.Platinum.Asixstore.Entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private int userId;
    private String nama;
    private String alamat;
    private long noTelepon;
    private String kota;
    private MultipartFile img;
    private List<Role> role;
}
