package com.Platinum.Asixstore.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
public class BarangDto {
    private int barangId;

    private String merk;

    private String seri;

    private String deskripsi;

    private String tipe;

    private Long hargaBarang;

    private Long hargaTawar;


}
