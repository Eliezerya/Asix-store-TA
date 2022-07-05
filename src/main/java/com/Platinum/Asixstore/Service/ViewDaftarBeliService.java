package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Entity.ViewDaftarBeli;
import com.Platinum.Asixstore.Repository.ViewDaftarBeliRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewDaftarBeliService {
    @Autowired
    ViewDaftarBeliRepo viewDaftarBeliRepo;

    public List<ViewDaftarBeli> display_daftar_beli(int userIdBuyer, String statusBarang){
        return viewDaftarBeliRepo.findByUserIdBuyerAndStatusBarang(userIdBuyer, statusBarang);
    }
}
