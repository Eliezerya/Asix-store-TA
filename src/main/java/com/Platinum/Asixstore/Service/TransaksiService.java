package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Entity.*;
import com.Platinum.Asixstore.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransaksiService {

    @Autowired
    BarangRepo barangRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StatusRepo statusRepo;

    @Autowired
    TransaksiRepo transaksiRepo;

    @Autowired
    RoleRepo roleRepo;



    //seller notifikasi
    public Barang notifikasi_seller(int barangId){
        Barang barangDitawar =barangRepo.findByBarangId(barangId);

        //cara tau kalau ada barang ditawar bagaimana ?????

        barangDitawar.getBarangId();
        barangDitawar.getNamaBarang();
        barangDitawar.getStatus();
        barangDitawar.getNamaBarang();
        barangDitawar.getBarangImg();
        barangDitawar.getHargaBarang();
        barangDitawar.getHargaTawar();
        barangDitawar.getTipeBarang();
        barangDitawar.getMerk();
        barangDitawar.getSeri();
        barangDitawar.getUpdatedAt();

        return barangDitawar;
    }

    //transaksi
    public Transaksi transaksi(int barangId, int userId){

        Barang barang = barangRepo.findByBarangId(barangId);
        Transaksi transaksi = new Transaksi();
        transaksi.setBarang(barang);
        transaksi.setHargaBarang(barang.getHargaBarang());
        transaksi.setNamaBarang(barang.getNamaBarang());
        Status status = statusRepo.findByStatusId(new Integer(2));
        transaksi.setStatus(status);
        Role role = roleRepo.findByIdRole(new Integer(1));
        transaksi.setRole(role);
        User user = userRepo.findById(userId);
        transaksi.setUser(user);
        transaksi.getUser().setUserId(user.getUserId());
        transaksi.setCreatedAt(new Date());
        transaksi.setUpdatedAt(new Date());
        return transaksiRepo.save(transaksi);
    }
}
