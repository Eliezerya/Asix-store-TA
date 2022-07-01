package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Repository.BarangRepo;
import com.Platinum.Asixstore.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaksiService {

    @Autowired
    BarangRepo barangRepo;

    @Autowired
    UserRepo userRepo;



    //seller notifikasi, belum bisa dilanjutkan karena butuh nama
    public Barang notifikasi_seller(int barangId){
        Barang barangDitawar =barangRepo.findByBarangId(barangId);

        //cara tau kalau ada barang ditawar bagaimana ?????

        barangDitawar.getBarangId();
        barangDitawar.getBuyer().getNama(); //bisa email atau nama, malah ga pake
        barangDitawar.getStatus();
        barangDitawar.getBarangImg();
        barangDitawar.getHargaBarang();
        barangDitawar.getHargaTawar();
        barangDitawar.getTipeBarang();
        barangDitawar.getMerk();
        barangDitawar.getSeri();
        barangDitawar.getUpdatedAt();

        return barangDitawar;
    }
}
