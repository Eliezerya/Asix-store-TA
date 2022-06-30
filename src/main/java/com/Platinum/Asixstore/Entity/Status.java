package com.Platinum.Asixstore.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "status_tabel")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="status_id")
    private int statusId;
    @Column(name = "status_barang")
    private String statusBarang;
}
