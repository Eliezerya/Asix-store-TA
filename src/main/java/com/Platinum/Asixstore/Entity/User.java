package com.Platinum.Asixstore.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_tabel")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;
    @Column(name = "nama")
    private String nama;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "no_Telepon")
    private long noTelepon;
    @Column(name = "kota")
    private String kota;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;
}
