package com.Platinum.Asixstore.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Setter
@Getter
@Table(name = "barang_table")
public class Barang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "barang_id")
    private int barangId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "nama_barang")
    private String namaBarang;
    @Column(name = "merk")
    private String merk;
    @Column(name = "seri")
    private String seri;
    @Column(name = "deskripsi")
    private String deskripsi;
    @Column(name = "tipe_barang")
    private String tipeBarang;
    @Lob
    private byte[] barangImg;
    @Column(name = "harga_barang")
    private Long hargaBarang;
    @Column(name = "harga_tawar")
    private Long hargaTawar;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "status_master",
            joinColumns = @JoinColumn(name = "barang_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private List<Status> status;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinTable(name = "barang_buyer",
            joinColumns = @JoinColumn(name = "barang_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User buyer;
}
