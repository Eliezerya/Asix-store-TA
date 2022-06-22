package com.Platinum.Asixstore.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class BuyerDto {
    private int userId;
    private String nama;
    private String email;
    private String password;
    private String alamat;
    private long noTelepon;
    private String kota;
    private MultipartFile img;
}
