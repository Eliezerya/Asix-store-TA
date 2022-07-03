package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Entity.*;
import com.Platinum.Asixstore.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        transaksi.getBarang().setBarangId(barangId);
        transaksi.setHargaBarang(barang.getHargaBarang());
        transaksi.setNamaBarang(barang.getNamaBarang());

        List<Status> getStatus = statusRepo.findByStatusId(2);
        barang.setStatus(getStatus);
//        transaksi.setStatus(getStatus);
        Role role = roleRepo.findByIdRole(new Integer(1));
        transaksi.setRole(role);
        transaksi.getRole().setIdRole(1);
        User user = userRepo.findById(userId);
        transaksi.setUser(user);
        transaksi.getUser().setUserId(user.getUserId());
        transaksi.setCreatedAt(new Date());
//        transaksi.setUpdatedAt(new Date());
        return transaksiRepo.save(transaksi);
    }
}
