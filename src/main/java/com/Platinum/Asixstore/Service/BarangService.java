package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Repository.BarangRepo;
import com.Platinum.Asixstore.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class BarangService {
    @Autowired
    BarangRepo barangRepo;
    @Autowired
    UserRepo userRepo;

    public Barang submit_barang(int userId, BarangDto barangDto)throws IOException {
        Barang barang = new Barang();
        User user = userRepo.findById(userId);
        barangDto.setUserId(userId);
        barang.setUser(user);
        barang.setMerk(barangDto.getMerk());
        barang.setSeri(barangDto.getSeri());
        barang.setDeskripsi(barangDto.getDeskripsi());
        barang.setTipeBarang(barangDto.getTipeBarang());
        barang.setBarangImg(barangDto.getBarangImg().getBytes());
        barang.setStock(barangDto.getStock());
        barang.setHargaBarang(barangDto.getHargaBarang());
        barang.setHargaTawar(barangDto.getHargaBarang());

        return barangRepo.save(barang);
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
