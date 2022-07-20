package com.Platinum.Asixstore.Controller;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Dto.TransaksiDto;
import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Entity.Transaksi;
import com.Platinum.Asixstore.Repository.BarangRepo;
import com.Platinum.Asixstore.Repository.TransaksiRepo;
import com.Platinum.Asixstore.Repository.UserRepo;
import com.Platinum.Asixstore.Service.TransaksiService;
import com.Platinum.Asixstore.Service.ViewBarangService;
import com.Platinum.Asixstore.Service.ViewDaftarBeliService;
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
    ViewDaftarBeliService viewDaftarBeliService;
    @Autowired
    BarangRepo barangRepo;

    public Authentication authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    @GetMapping("/transaksi/{userId}/{barangId}")
    public ResponseEntity<?> transaksi_seller_buyer(@PathVariable("barangId") int barangId, @PathVariable("userId") int userId) {
        if (authentication().getPrincipal().toString().equalsIgnoreCase(barangRepo.findByBarangId(barangId).getUser().getEmail())){
            transaksiService.transaksiTerima(barangId, userId);
            return new ResponseEntity<>("Transaksi Berhasil !", HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>("Don't have any Access", HttpStatus.NO_CONTENT);
        }
    }
    @DeleteMapping ("/transaksi/tolak/{userId}/{barangId}")
    public ResponseEntity<?> transaksi_tolak(@PathVariable("barangId")int barangId, @PathVariable("userId") int userId){
        if (authentication().getPrincipal().toString().equalsIgnoreCase(barangRepo.findByBarangId(barangId).getUser().getEmail())){
            transaksiService.transaksi_tolak(barangId, userId);
            return new ResponseEntity<>("Penawaran Ditolak !", HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>("Don't have any Access", HttpStatus.NO_CONTENT);
        }
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

    @RequestMapping(value = "/daftar-beli/{userIdBuyer}/{statusBarang}", method = RequestMethod.GET)
    public ResponseEntity<?> display_daftarbeliBuyer(@PathVariable("userIdBuyer") int userIdBuyer, @PathVariable("statusBarang") String statusBarang) {
        return new ResponseEntity<>(viewDaftarBeliService.display_daftar_beli(userIdBuyer, statusBarang), HttpStatus.ACCEPTED);
    }

    @GetMapping("/transaksi-detail/{barangId}")
    public ResponseEntity<?> transaksi_byBarangId(@PathVariable("barangId") int barangId) {
        if (authentication().getPrincipal().toString().equalsIgnoreCase(barangRepo.findByBarangId(barangId).getUser().getEmail())) {
            return new ResponseEntity<>(transaksiService.view_transaksi_BarangId(barangId), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Don't have any Access", HttpStatus.NO_CONTENT);
        }
    }

}