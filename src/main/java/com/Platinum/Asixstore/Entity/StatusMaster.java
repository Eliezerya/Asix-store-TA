package com.Platinum.Asixstore.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status_master")
@Getter
@Setter
public class StatusMaster {
    @Id
    @Column(name = "barang_id")
    private int barangId;
    @Column(name = "status_id")
    private int statusId;


    
}
