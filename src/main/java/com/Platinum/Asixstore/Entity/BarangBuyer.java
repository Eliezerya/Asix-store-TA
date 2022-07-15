package com.Platinum.Asixstore.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "barang_buyer")
public class BarangBuyer {
    @Id
    @Column(name = "barang_id")
    private int barangId;
    @Column(name = "user_id")
    private int userId;
}
