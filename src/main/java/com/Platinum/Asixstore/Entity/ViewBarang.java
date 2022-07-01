package com.Platinum.Asixstore.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "view_barang")
public class ViewBarang {
    @Id
    @Column(name = "barang_id")
    private int barangId;
    @Lob
    @Column(name = "barang_img")
    private byte[] barangImg;
    @Column(name = "deskripsi")
    private String deskripsi;
    @Column(name = "status_barang")
    private String statusBarang;
    @Column(name = "tipe_barang")
    private String tipeBarang;
    @Column(name = "harga_barang")
    private long hargaBarang;
    @Column(name = "no_telepon")
    private long noTelepon;
    @Column(name = "email_seller")
    private String emailSeller;
    @Column(name = "kota")
    private String kota;
    @Column(name = "merk")
    private String merk;
    @Column(name = "seri")
    private String seri;
}
