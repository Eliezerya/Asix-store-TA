package com.Platinum.Asixstore.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaksi_tabel")
@Getter
@Setter
public class Transaksi {

    @Id
    @Column(name = "transaksi_id")
    private int transaksiId;

}
