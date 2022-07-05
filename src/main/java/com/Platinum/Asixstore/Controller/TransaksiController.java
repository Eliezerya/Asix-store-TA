package com.Platinum.Asixstore.Controller;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Dto.TransaksiDto;
import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Entity.Transaksi;
import com.Platinum.Asixstore.Repository.BarangRepo;
import com.Platinum.Asixstore.Repository.TransaksiRepo;
import com.Platinum.Asixstore.Service.TransaksiService;
import com.Platinum.Asixstore.Service.ViewBarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@Transactional
public class TransaksiController {
    @Autowired
    TransaksiService transaksiService;
    @Autowired
    TransaksiRepo transaksiRepo;
    @Autowired
    BarangRepo barangRepo;
    @Autowired
    ViewBarangService viewBarangService;

    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }
    @RequestMapping(value = "/barang/notifikasi/{userIdSeller}/{statusBarang}" , method = RequestMethod.GET)
    public ResponseEntity<?> notifikasi_seller(@PathVariable int userIdSeller,@PathVariable String statusBarang) {
        return new ResponseEntity<>(transaksiService.notifikasi_seller(userIdSeller,statusBarang), HttpStatus.ACCEPTED);
    }
    @GetMapping("/transaksi/{userId}/{barangId}")
    public ResponseEntity<?> transaksi_seller_buyer(@PathVariable("barangId") int barangId, @PathVariable("userId") int userId) {
        transaksiService.transaksi(barangId, userId);
        return new ResponseEntity<>("Transaksi Berhasil !", HttpStatus.ACCEPTED);
    }

    @PutMapping("/barang/tawar/{barangId}") // tawar barang untuk buyer
    public ResponseEntity<?> beli_tawar_harga(@PathVariable("barangId") int barangId, BarangDto barangDto) {
        if (authentication().getPrincipal().toString().equalsIgnoreCase(barangRepo.findByBarangId(barangId).getUser().getEmail())){
            return new ResponseEntity<>("Anda tidak dapat membeli barang sendiri", HttpStatus.FORBIDDEN);
        }else {
            transaksiService.update_harga_tawar(barangId, barangDto);
            return new ResponseEntity<>("barang telah ditawar", HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/daftar-jual/{statusId}")
    public ResponseEntity<?> display_barangbyStatus(@PathVariable("statusId") int statusId) throws Exception {
        return new ResponseEntity<>(viewBarangService.view_barang_bystatus(statusId), HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/daftar-jual/{userId}/{statusId}", method = RequestMethod.GET)//tampilkan semua barang
    public ResponseEntity<?> display_barangbySellerandStatus(@PathVariable("userId") int userId,@PathVariable("statusId") int statusId) throws Exception {
        return new ResponseEntity<>(viewBarangService.view_barang_bysellerandstatus(userId,statusId), HttpStatus.ACCEPTED);
    }
}