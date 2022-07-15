package com.Platinum.Asixstore.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notifikasi_tabel")
@Getter
@Setter
public class Notifikasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notifikasi_id")
    private int notifikasiId;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "barang_id")
    private Barang barangId;
    @JoinColumn(name = "nama_barang")
    private String namaBarang;
    @JoinColumn(name = "harga_barang")
    private long hargaBarang;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_buyer")
    private User buyer;
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_seller")
    private User seller;
}
