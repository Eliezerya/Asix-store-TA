package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Repository.BarangRepo;
import com.Platinum.Asixstore.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BarangService {
    @Autowired
    BarangRepo barangRepo;
    @Autowired
    UserRepo userRepo;

    public Barang submit_barang(BarangDto barangDto) throws IOException {
        User user = new User();
        Barang barang = new Barang();
        barang.setUser(user);
        barang.setMerk(barangDto.getMerk());
        barang.setSeri(barangDto.getSeri());
        barang.setDeskripsi(barangDto.getDeskripsi());
        barang.setTipeBarang(barangDto.getTipeBarang());
        barang.setBarangImg(barangDto.getBarangImg().getBytes());
        barang.setStock(barangDto.getStock());
        barang.setHargaBarang(barangDto.getHargaBarang());
        barang.setHargaTawar(barangDto.getHargaTawar());
        return barangRepo.save(barang);
    }

    public List<Barang> display_barang() {
        List<Barang> listBarang = barangRepo.findAll();
        return listBarang;
    }

    public List<Barang> filter_barang(String tipeBarang) throws Exception {
        return barangRepo.findByTipeBarang(tipeBarang);
    }

    public Barang update_harga_tawar(int barangId, BarangDto barangDto) {
        Barang update = barangRepo.findByBarangId(barangId);
        update.setHargaTawar(barangDto.getHargaTawar());
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
}
