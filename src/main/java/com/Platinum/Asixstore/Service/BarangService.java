package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Entity.Status;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Repository.BarangRepo;
import com.Platinum.Asixstore.Repository.StatusRepo;
import com.Platinum.Asixstore.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    StatusRepo statusRepo;
    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    public Barang submit_barang(int userId, BarangDto barangDto)throws IOException {


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


        return barangRepo.save(barang);
    }


    public Barang update_harga_tawar(int barangId, BarangDto barangDto) {
        Barang update = barangRepo.findByBarangId(barangId);

        User Buyer = userRepo.findByEmail(authentication().getPrincipal().toString());

        update.setHargaTawar(barangDto.getHargaTawar());
        List<Status> getStatus = statusRepo.findByStatusId(3);
        update.setStatus(getStatus);
        update.setUpdatedAt(new Date());
        update.setBuyer(Buyer);
        Barang barang =barangRepo.save(update);
        return barang;
    }

    public boolean delete_barang(int barangId){
        Barang barang = barangRepo.findByBarangId(barangId);
        if (barang != null) {
            barangRepo.deleteById(barangId);
            return true;
        }else {
            return false;
        }
    }

    public Barang display_barang_byId(int barangId) throws IOException{
        return barangRepo.findByBarangId(barangId);
    }

    public void edit_barang(int barangId, int userId, BarangDto barangDto)throws IOException{
            Barang barang = barangRepo.findByBarangId(barangId);
            User user = userRepo.findById(userId);
            barang.setUser(user);
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
