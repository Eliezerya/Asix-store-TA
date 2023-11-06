package com.Platinum.Asixstore.Service;

import com.Platinum.Asixstore.Entity.RekomendasiUser;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Entity.ViewBarang;
import com.Platinum.Asixstore.Entity.ViewBarangApriori;
import com.Platinum.Asixstore.Repository.RekomendasiUserRepo;
import com.Platinum.Asixstore.Repository.UserRepo;
import com.Platinum.Asixstore.Repository.ViewBarangAprioriRepo;
import com.Platinum.Asixstore.Repository.ViewBarangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AprioriService {

    @Autowired
    ViewBarangRepo viewBarangRepo;

    @Autowired
    RekomendasiUserRepo rekomendasiUserRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ViewBarangAprioriRepo viewBarangAprioriRepo;

    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    public List<ViewBarangApriori> view_apriori() {

        User akun = userRepo.findByEmail(authentication().getPrincipal().toString());
        System.out.println("email User Akun::"+authentication().getPrincipal().toString());

        RekomendasiUser rekomendasiUser = rekomendasiUserRepo.findByUserId(akun.getUserId());

        System.out.println("Rekomendasi untuk buyer :"+rekomendasiUser.getRekomendasi());
        if (rekomendasiUser.getRekomendasi() == null){

        }
        List<ViewBarangApriori> barangAprioris = viewBarangAprioriRepo.findAllByStatusIdAndTipeBarang( 1,rekomendasiUser.getRekomendasi());
        System.out.println("Isi Barang :" + barangAprioris.get(0).getNamaBarang());
        System.out.println("ID Barang :" + barangAprioris.get(0).getBarangId());
        return barangAprioris;
    }
}
