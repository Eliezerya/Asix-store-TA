package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Entity.*;
import com.Platinum.Asixstore.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    ViewNotifikasiRepo viewNotifikasiRepo;

    @Autowired
    ViewNotifikasiBuyerRepo viewNotifikasiBuyerRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    NotifikasiRepo notifikasiRepo;

    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    public Barang update_harga_tawar(int barangId, BarangDto barangDto) {
        Barang tawar = barangRepo.findByBarangId(barangId);
        Notifikasi notifikasi = new Notifikasi();

        User buyer = userRepo.findByEmail(authentication().getPrincipal().toString());
        tawar.setHargaTawar(barangDto.getHargaTawar());
        List<Status> getStatus = statusRepo.findByStatusId(3);
        tawar.setStatus(getStatus);
        tawar.setBuyer(buyer);
        tawar.setUpdatedAt(new Date());

        notifikasi.setBarang(tawar);
        notifikasi.setHargaBarang(tawar.getHargaBarang());
        notifikasi.setBuyer(buyer);
        notifikasi.setNamaBarang(tawar.getNamaBarang());
        notifikasi.setUpdatedAt(tawar.getUpdatedAt());
        notifikasi.setCreatedAt(tawar.getCreatedAt());
        notifikasi.setSeller(tawar.getUser());
        notifikasiRepo.save(notifikasi);
        return barangRepo.save(tawar);
    }


    //seller notifikasi
    public List<ViewNotifikasi> notifikasi_seller(int userIdSeller, String statusBarang) {
        return viewNotifikasiRepo.findByUserIdSellerAndStatusBarang(userIdSeller,statusBarang);
    }

    public List<ViewNotifikasiBuyer> notifikasi_buyer(int userIdBuyer, String statusBarang) {
        return viewNotifikasiBuyerRepo.findByUserIdBuyerAndStatusBarang(userIdBuyer,statusBarang);
    }

    //transaksi
    // blocker, cara untuk memasukan buyer sewaktu menawar barang
    public Transaksi transaksi(int barangId, int userId) {

        Barang barang = barangRepo.findByBarangId(barangId);
        Transaksi transaksi = new Transaksi();
        transaksi.setBarang(barang);
        transaksi.getBarang().setBarangId(barangId);
        transaksi.setHargaBarang(barang.getHargaTawar());
        transaksi.setNamaBarang(barang.getNamaBarang());

        List<Status> getStatus = statusRepo.findByStatusId(2);
        barang.setStatus(getStatus);

        User user = userRepo.findById(userId);
        transaksi.setSeller(user);
        transaksi.getSeller().setUserId(user.getUserId());
        transaksi.setCreatedAt(new Date());
        User buyer = barang.getBuyer();
        transaksi.setBuyer(buyer);
        return transaksiRepo.save(transaksi);
    }
}
