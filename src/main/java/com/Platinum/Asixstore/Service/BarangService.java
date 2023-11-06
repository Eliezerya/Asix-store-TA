package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Entity.*;
import com.Platinum.Asixstore.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class BarangService {
    @Autowired
    BarangRepo barangRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;
    @Autowired
    StatusRepo statusRepo;

    @Autowired
    ViewBarangRepo viewBarangRepo;

    @Autowired
    AprioriRepo aprioriRepo;

    @Autowired
    StatusMasterRepo statusMasterRepo;

    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    public Barang submit_barang(int userId, BarangDto barangDto) throws IOException {

        List<ViewBarang> cekCountAvailabel = viewBarangRepo.findByUserIdAndStatusId(userId, 1);
        List<ViewBarang> cekCountBidding = viewBarangRepo.findByUserIdAndStatusId(userId, 3);
        int availabel = cekCountAvailabel.size();
        int bidding = cekCountBidding.size();
        int selling = availabel + bidding;
        if (selling <  4){
            Barang barang = new Barang();
            User user = userRepo.findById(userId);
            barangDto.setUserId(userId);
            barang.setUser(user);
            List<Status> getStatus = statusRepo.findByStatusId(1);
            barang.setStatus(getStatus);
            barang.setNamaBarang(barangDto.getNamaBarang());
            barang.setMerk(barangDto.getMerk());
            barang.setSeri(barangDto.getSeri());
            barang.setDeskripsi(barangDto.getDeskripsi());
            barang.setTipeBarang(barangDto.getTipeBarang().toUpperCase(Locale.ROOT));
            barang.setBarangImg(barangDto.getBarangImg().getBytes());
            barang.setHargaBarang(barangDto.getHargaBarang());
            barang.setHargaTawar(barangDto.getHargaBarang());
            barang.setCreatedAt(new Date());

            Apriori apriori = new Apriori();
            if (aprioriRepo.findAllByBarang(barang.getTipeBarang().toUpperCase()) == null){
                apriori.setBarang(barang.getTipeBarang().toUpperCase());
                apriori.setRekomendasi("DUMMY");
                apriori.setSupport(0);
                aprioriRepo.save(apriori);
            }


            return barangRepo.save(barang);
        }else {
            return null;
        }
    }


    @Modifying
    public boolean delete_barang(int barangId) {
        Barang barang = barangRepo.findByBarangId(barangId);
        if (barang != null) {
            System.out.println("DELETED");
            statusMasterRepo.deleteNative(barangId);
            barangRepo.deleteById(barangId);
            return true;
        } else {
            System.out.println("FALSE");
            return false;
        }
    }

    public Barang display_barang_byId(int barangId) throws IOException {
        return barangRepo.findByBarangId(barangId);
    }

    public void edit_barang(int barangId, int userId, BarangDto barangDto) throws IOException {
        Barang barang = barangRepo.findByBarangId(barangId);
        User user = userRepo.findById(userId);
        barang.setUser(user);
        barang.setNamaBarang(barangDto.getNamaBarang());
        barang.setMerk(barangDto.getMerk());
        barang.setSeri(barangDto.getSeri());
        barang.setDeskripsi(barangDto.getDeskripsi());
        barang.setTipeBarang(barangDto.getTipeBarang());
        barang.setBarangImg(barangDto.getBarangImg().getBytes());
        barang.setHargaBarang(barangDto.getHargaBarang());
        barang.setHargaTawar(barangDto.getHargaBarang());
        barangRepo.save(barang);
    }


}
