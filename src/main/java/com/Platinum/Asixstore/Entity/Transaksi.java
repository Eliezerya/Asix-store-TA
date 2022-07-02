package com.Platinum.Asixstore.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaksi_tabel")
@Getter
@Setter
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaksi_id")
    private int transaksiId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "status_id")
    private Status status;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "barang_id")
    private Barang barang;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @JoinColumn(name = "nama_barang")
    private String namaBarang;
    @JoinColumn(name = "harga_barang")
    private long hargaBarang;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

}
