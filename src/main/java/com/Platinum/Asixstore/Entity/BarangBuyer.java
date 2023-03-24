package com.Platinum.Asixstore.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
}
