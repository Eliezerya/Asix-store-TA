package com.Platinum.Asixstore.Controller;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Entity.Status;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Entity.ViewBarang;
import com.Platinum.Asixstore.Repository.BarangRepo;
import com.Platinum.Asixstore.Repository.StatusRepo;
import com.Platinum.Asixstore.Repository.UserRepo;
import com.Platinum.Asixstore.Service.BarangService;
import com.Platinum.Asixstore.Service.ViewBarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
@Transactional
public class BarangController {
    //tesdoang

    @Autowired
    BarangService barangService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BarangRepo barangRepo;
    @Autowired
    ViewBarangService viewBarangService;

    @Autowired
    StatusRepo statusRepo;
    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    @PostMapping("/barang/{userId}/daftar") // barang submit
    public ResponseEntity<?> submit_barang(@PathVariable int userId, BarangDto barangDto, @RequestParam("barangImg") MultipartFile fileUpload) throws IOException {
        User userToken = userRepo.findById(userId);

        if (userToken.getEmail().equalsIgnoreCase(authentication().getPrincipal().toString())) {
            barangDto.setBarangImg(fileUpload);
            Barang barang = barangService.submit_barang(userId, barangDto);
            if (barang != null){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>("Barang dijual melebihi batas maksimum. Batas Maksimum Penjualan adalah 4!",HttpStatus.ACCEPTED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping("barang/delete/{barangId}") //delete barang
    public ResponseEntity<?> hapus_barang(@PathVariable("barangId") int barangId) {
        Barang barang = barangRepo.findByBarangId(barangId);
        User user = userRepo.findById(barang.getUser().getUserId());

        if (authentication().getPrincipal().toString().equalsIgnoreCase(barang.getUser().getEmail())) {
            boolean barang_status = barangService.delete_barang(barangId);
            if (barang_status) {
                return new ResponseEntity<>(barang_status, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(barang_status, HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/barang/update/{userId}/{barangId}") // update isi barang
    public ResponseEntity<?> update_barang(@PathVariable("barangId") int barangId, @PathVariable("userId") int userId, @RequestParam("barangImg") MultipartFile fileUpload, BarangDto barangDto) throws IOException {
        Barang barang = barangRepo.findByBarangId(barangId);

        if (authentication().getPrincipal().toString().equalsIgnoreCase(barang.getUser().getEmail())) {
            barangDto.setBarangImg(fileUpload);
            barangService.edit_barang(barangId, userId, barangDto);
            return new ResponseEntity<>("Barang telah di update",HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



}