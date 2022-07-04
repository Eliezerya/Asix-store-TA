package com.Platinum.Asixstore.Dto;

import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Entity.Role;
import com.Platinum.Asixstore.Entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class TransaksiDto {

    private int transaksiId;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn (name = "status_id")
//    private List<Status> status;
    private Barang barang;
    private Role role;
    private User user;
    private String namaBarang;
    private long hargaBarang;
    private Date createdAt;
    private Date updatedAt;
}
