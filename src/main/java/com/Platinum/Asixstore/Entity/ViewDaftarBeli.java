package com.Platinum.Asixstore.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "view_notifikasi_seller")
public class ViewDaftarBeli {
    @Id
    @Column(name = "barang_id")
    private int barangId;
    @Column(name = "user_id_buyer")
    private int userIdBuyer;
    @Column(name = "user_id_seller")
    private int userIdSeller;
    @Column(name = "nama_barang")
    private String namaBarang;
    @Column(name = "status_barang")
    private String statusBarang;
    @Column(name = "harga_barang")
    private long hargaBarang;
    @Column(name = "harga_tawar")
    private long hargaTawar;
    @Lob
    @Column(name = "gambar_barang")
    private byte[] gambarBarang;
    @Column(name = "tanggal_tawar")
    private Date tanggalTransaksi;
    @Column(name = "tipe_barang")
    private String tipeBarang;
}
