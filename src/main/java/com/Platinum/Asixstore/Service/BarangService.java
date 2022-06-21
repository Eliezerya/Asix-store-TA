package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Repository.BarangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BarangService {
    @Autowired
    BarangRepo barangRepo;

    public Barang submit_barang(BarangDto barangDto)throws IOException {
        Barang barang = new Barang();
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

    public List<Barang> display_barang(){
        List<Barang> listBarang = barangRepo.findAll();
        return listBarang;
    }

    public List<Barang> filter_barang(String tipeBarang) throws Exception{

        return barangRepo.findByTipeBarang(tipeBarang);


    }
}
