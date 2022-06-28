package com.Platinum.Asixstore.Controller;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Entity.User;
import com.Platinum.Asixstore.Repository.BarangRepo;
import com.Platinum.Asixstore.Repository.UserRepo;
import com.Platinum.Asixstore.Service.BarangService;
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
    @Autowired
    BarangService barangService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BarangRepo barangRepo;

    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }


    @PostMapping("/barang/{userId}/daftar") //Submit barang
    public ResponseEntity<?> submit_barang(@PathVariable int userId, BarangDto barangDto, @RequestParam("barangImg") MultipartFile fileUpload) throws IOException {
        User userToken = userRepo.findById(userId);
        if (userToken.getEmail().equalsIgnoreCase(authentication().getPrincipal().toString())) {
            barangDto.setBarangImg(fileUpload);
            Barang barang = barangService.submit_barang(userId, barangDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/barang") //tampilkan semua barang
    public ResponseEntity<?> display_barang() {
        return new ResponseEntity<>(barangService.display_barang(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/barang/{tipeBarang}", method = RequestMethod.GET) // kategori gitar dan aksesoris
    public ResponseEntity<?> filter_barang(@PathVariable("tipeBarang") String tipeBarang) throws Exception {
        List<Barang> barangFilter = barangService.filter_barang(tipeBarang.toLowerCase(Locale.ROOT));
        return new ResponseEntity<>(barangFilter, HttpStatus.ACCEPTED);
    }

    @PutMapping("/barang/update/{barangId}") // tawar barang untuk buyer
    public ResponseEntity<?> beli_tawar_harga(@PathVariable("barangId") int barangId, BarangDto barangDto) {
        Barang barang = barangService.update_harga_tawar(barangId, barangDto);
        return new ResponseEntity<>(barang, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("barang/delete/{barangId}") //delete barang
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

    @PutMapping("/barang/update/{userId}/{barangId}") //
    public ResponseEntity<?> update_barang(@PathVariable("barangId") int barangId, @PathVariable("userId") int userId, @RequestParam("barangImg") MultipartFile fileUpload, BarangDto barangDto) throws IOException {
        barangDto.setBarangImg(fileUpload);
        barangService.edit_barang(barangId, userId, barangDto);
        Barang response = barangService.display_barang_byId(barangId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
