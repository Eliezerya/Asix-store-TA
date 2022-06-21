package com.Platinum.Asixstore.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class BarangDto {
    private int barangId;
    private String merk;
    private String seri;
    private String deskripsi;
    private String tipeBarang;
    private MultipartFile barangImg;
    private int stock;
    private Long hargaBarang;
    private Long hargaTawar;


}
