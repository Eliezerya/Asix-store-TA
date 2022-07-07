package com.Platinum.Asixstore.Controller;


import com.Platinum.Asixstore.Repository.TransaksiRepo;
import com.Platinum.Asixstore.Repository.UserRepo;
import com.Platinum.Asixstore.Service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotifikasiController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    TransaksiService transaksiService;

    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    @RequestMapping(value = "/user/notifikasi/{userIdSeller}/{statusBarang}" , method = RequestMethod.GET)
    public ResponseEntity<?> notifikasi_seller(@PathVariable int userIdSeller, @PathVariable String statusBarang) {
        if(authentication().getPrincipal().toString().equalsIgnoreCase(userRepo.findById(userIdSeller).getEmail())){
            return new ResponseEntity<>(transaksiService.notifikasi_seller(userIdSeller,statusBarang), HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
