package com.Platinum.Asixstore.Dto;

import com.Platinum.Asixstore.Entity.Role;
import com.Platinum.Asixstore.Entity.Status;
import com.Platinum.Asixstore.Entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class BarangDto {
    private int barangId;
    private int userId;
    private String namaBarang;
    private String merk;
    private String seri;
    private String deskripsi;
    private String tipeBarang;
    private MultipartFile barangImg;
    private Long hargaBarang;
    private Long hargaTawar;
    private User user;
    private List<Status> status;
    private Role roleId;


}
