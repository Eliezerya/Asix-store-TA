package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Dto.TransaksiDto;
import com.Platinum.Asixstore.Entity.*;
import com.Platinum.Asixstore.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
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

    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    public Barang update_harga_tawar(int barangId, BarangDto barangDto) {
        Barang tawar = barangRepo.findByBarangId(barangId);
        User buyer = userRepo.findByEmail(authentication().getPrincipal().toString());
        tawar.setHargaTawar(barangDto.getHargaTawar());
        List<Status> getStatus = statusRepo.findByStatusId(3);
        tawar.setStatus(getStatus);
        tawar.setBuyer(buyer);
        tawar.setUpdatedAt(new Date());
        return barangRepo.save(tawar);
    }


    //seller notifikasi
    public Barang notifikasi_seller(int barangId) {
        Barang barangDitawar = barangRepo.findByBarangId(barangId);

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
        barangDitawar.getBuyer().getNama();

        return barangDitawar;
    }

    //transaksi
    // blocker, cara untuk memasukan buyer sewaktu menawar barang
    public Transaksi transaksi(int barangId, int userId) {

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
        return transaksiRepo.save(transaksi);
    }
}
