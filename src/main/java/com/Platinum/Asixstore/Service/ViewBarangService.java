package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Entity.ViewBarang;
import com.Platinum.Asixstore.Repository.ViewBarangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewBarangService {

    @Autowired
    ViewBarangRepo viewBarangRepo;

    public List<ViewBarang> view_semua_barang(){
        return viewBarangRepo.findAll();
    }
    public List<ViewBarang> filter_barang(String tipeBarang) throws Exception {
        return viewBarangRepo.findByTipeBarang(tipeBarang);
    }

    public List<ViewBarang> view_barang_bysellerandstatus(int userId, int statusId) throws Exception {
        return viewBarangRepo.findByUserIdAndStatusId(userId,statusId);
    }

    public List<ViewBarang> view_barang_bystatus(int statusId) throws Exception {
        return viewBarangRepo.findByStatusId(statusId);
    }
}
