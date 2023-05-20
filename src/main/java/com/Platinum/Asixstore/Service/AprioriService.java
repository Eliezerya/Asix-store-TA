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

        Optional<RekomendasiUser> rekomendasiUser = rekomendasiUserRepo.findById(akun.getUserId());

        return viewBarangAprioriRepo.findByStatusIdAndTipeBarang( 1,rekomendasiUser.get().getRekomendasi());
    }
}
