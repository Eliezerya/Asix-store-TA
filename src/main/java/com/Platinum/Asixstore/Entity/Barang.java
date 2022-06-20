package com.Platinum.Asixstore.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "barang_table")
public class Barang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "barang_id")
    private int barangId;
    @Column(name = "merk")
    private String merk;
    @Column(name = "seri")
    private String seri;
    @Column(name = "deskripsi")
    private String deskripsi;
    @Column(name = "tipe_barang")
    private String tipe;
    @Lob
    private byte[] barangImg;
    @Column(name="stock")
    private int stock;
    @Column(name = "harga_barang")
    private Long hargaBarang;
    @Column(name = "harga_tawar")
    private Long hargaTawar;

}
