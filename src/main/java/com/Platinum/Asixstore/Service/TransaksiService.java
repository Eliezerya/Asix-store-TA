package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Entity.*;
import com.Platinum.Asixstore.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class TransaksiService {

    @Autowired
    BarangRepo barangRepo;

    @Autowired
    AprioriRepo aprioriRepo;


    @Autowired
    UserRepo userRepo;

    @Autowired
    StatusRepo statusRepo;

    @Autowired
    TransaksiRepo transaksiRepo;

    @Autowired
    RekomendasiUserRepo rekomendasiUserRepo;

    @Autowired
    ViewNotifikasiRepo viewNotifikasiRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    NotifikasiRepo notifikasiRepo;

    @Autowired
    BarangBuyerRepo barangBuyerRepo;

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

        notifikasi.setBarangId(tawar);
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
        return viewNotifikasiRepo.findByUserIdSellerAndStatusBarang(userIdSeller, statusBarang);
    }

    public List<ViewNotifikasi> notifikasi_buyer(int userIdBuyer, String statusBarang) {
        return viewNotifikasiRepo.findByUserIdBuyerAndStatusBarang(userIdBuyer, statusBarang);
    }

    public Transaksi transaksiTerima(int barangId, int seller) {

        Barang barang = barangRepo.findByBarangId(barangId);
        Transaksi transaksi = new Transaksi();
        transaksi.setBarang(barang);
        transaksi.getBarang().setBarangId(barangId);
        transaksi.setHargaBarang(barang.getHargaTawar());
        transaksi.setNamaBarang(barang.getNamaBarang());

        //sold
        List<Status> getStatus = statusRepo.findByStatusId(2);
        barang.setStatus(getStatus);

        User user = userRepo.findById(seller);
        transaksi.setSeller(user);
        transaksi.getSeller().setUserId(user.getUserId());
        transaksi.setCreatedAt(new Date());
        User buyer = barang.getBuyer();
        transaksi.setBuyer(buyer);

        //Apriori Logic

        String namaTypeBarangBaru = barang.getTipeBarang().toUpperCase();
        List<BarangBuyer> barangBuyerLs = barangBuyerRepo.findAllByUserId(buyer.getUserId());

        List<Apriori> aprioriCheck = aprioriRepo.findAllByBarang(namaTypeBarangBaru);
        List<Apriori> rekomendasiAprioriCheck = aprioriRepo.findAllByRekomendasi(namaTypeBarangBaru);
        List<Apriori> aprioris = new ArrayList<>();

        if (!aprioriCheck.isEmpty()) {
            aprioris = aprioriCheck;
        } else if (!rekomendasiAprioriCheck.isEmpty()) {
            aprioris = rekomendasiAprioriCheck;
        }

        if (!aprioris.isEmpty()) {
            //Not Null
            System.out.println("masuk ke not null");
            if (aprioris.size() == 1) {
                //kondisi kalau barang di apriori == 1
                aprioris.get(0).setBarang(namaTypeBarangBaru);
                aprioris.get(0).setRekomendasi(aprioris.get(0).getRekomendasi().toUpperCase());
                aprioris.get(0).setSupport(aprioris.get(0).getSupport() + 1);
                aprioriRepo.save(aprioris.get(0));
            } else {
                //kondisi kalau barang di apriori > 1
                Barang barangUser;
                int indexApriori = 0;
                for (BarangBuyer bb : barangBuyerLs) {
                    barangUser = barangRepo.findByBarangId(bb.getBarangId());

                    String barangRekomendasiDariApriori = aprioriCheck.get(indexApriori).getRekomendasi();
                    String barangYgPernahDibeli = barangUser.getTipeBarang();

                    if (barangRekomendasiDariApriori.equalsIgnoreCase(barangYgPernahDibeli)) {
                        Apriori apriori = aprioriRepo.findByBarangAndRekomendasi(namaTypeBarangBaru, barangYgPernahDibeli);
                        apriori.setSupport(apriori.getSupport() + 1);
                        aprioriRepo.save(apriori);
                        System.out.println("Sukses");
                    }
                }
            }

        } else {
            // Null
            System.out.println("masuk Null");
            Apriori newBarangApriori = new Apriori();

            newBarangApriori.setBarang(barang.getTipeBarang());

            Barang barangRekomendasi;
            for (BarangBuyer a : barangBuyerLs) {
                barangRekomendasi = barangRepo.findByBarangId(a.getBarangId());
                System.out.println("barang buyer :" + barangRekomendasi.getTipeBarang());

                if (!barangRekomendasi.getTipeBarang().equalsIgnoreCase(barang.getTipeBarang())) {
                    newBarangApriori.setRekomendasi(barangRekomendasi.getTipeBarang());
                    break;
                }
            }
            newBarangApriori.setSupport(1);
            aprioriRepo.save(newBarangApriori);

        }
        // logic rekomendasi barang from the max support

        int max = 0;
        String rekomendasiFix = "";
        int index = 1;
        for (BarangBuyer barangRekomendasi : barangBuyerLs) {

            Barang rekBarang = barangRepo.findByBarangId(barangRekomendasi.getBarangId());
            System.out.println("index barang buyer ls : " + index);
            index = index+1;
            Apriori apriori1 = aprioriRepo.findByBarang(rekBarang.getTipeBarang());
            System.out.println("sudah masuk ke apriori1 :" + apriori1.getRekomendasi());
            if (apriori1.getSupport()>max){
                rekomendasiFix = apriori1.getRekomendasi();
                max = apriori1.getSupport();
                System.out.println("max : " +max);
                System.out.println("rekomendasiFix : " + rekomendasiFix);
            }

        }
        //sett it up
        RekomendasiUser rekomendasiUser;
        Optional<RekomendasiUser> rekomendasiUserOptional = rekomendasiUserRepo.findById(buyer.getUserId());

        if (rekomendasiUserOptional.isPresent()) {
            rekomendasiUser = rekomendasiUserOptional.get();

        } else {
            rekomendasiUser = new RekomendasiUser();
            rekomendasiUser.setUserId(buyer.getUserId());
        }
        rekomendasiUser.setRekomendasi(rekomendasiFix);
        rekomendasiUserRepo.save(rekomendasiUser);

        System.out.println("barang rekomendasi : "+rekomendasiFix );
        return transaksiRepo.save(transaksi);
    }


    public Barang transaksi_tolak(int barangId, int userId) {
        notifikasiRepo.deleteNative(barangId);
        Barang barang = barangRepo.findByBarangId(barangId);
        List<Status> getStatus = statusRepo.findByStatusId(1);
        barang.setStatus(getStatus);
        barangBuyerRepo.deleteNative(barangId);

        return barangRepo.save(barang);
    }

    public List<ViewNotifikasi> view_transaksi_BarangId(int barangId) {
        return viewNotifikasiRepo.findByBarangId(barangId);
    }
}
