package com.Platinum.Asixstore.Controller;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class BarangController {
    @Autowired
    BarangService barangService;

    @PostMapping("/barang/{userId}/daftar")
    public ResponseEntity<?> submit_barang(@PathVariable int userId, BarangDto barangDto, @RequestParam("barangImg") MultipartFile fileUpload) throws IOException {
        if (userId == barangDto.getUserId()){
            barangDto.setBarangImg(fileUpload);
            Barang barang = barangService.submit_barang(userId,barangDto);
            return new ResponseEntity<>(barang, HttpStatus.CREATED);
        }else {
            String error = "login error cok";
            return new ResponseEntity<>(error,HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/barang")
    public ResponseEntity<?> display_barang() {
        List<Barang> barangs = barangService.display_barang();
        return new ResponseEntity<>(barangs, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/barang/{tipeBarang}", method = RequestMethod.GET)
    public ResponseEntity<?> filter_barang(@PathVariable("tipeBarang") String tipeBarang) throws Exception {
        List<Barang> barangFilter = barangService.filter_barang(tipeBarang);
        return new ResponseEntity<>(barangFilter, HttpStatus.ACCEPTED);
    }

    @PutMapping("/barang/update/{barangId}")
    public ResponseEntity<?> beli_tawar_harga(@PathVariable("barangId") int barangId,BarangDto barangDto) {
        Barang barang = barangService.update_harga_tawar(barangId,barangDto);
        return new ResponseEntity<>(barang, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/barang/delete/{barangId}")
    public ResponseEntity<?> hapus_barang(@PathVariable("barangId") int barangId){
        boolean barang_status = barangService.delete_barang(barangId);
        if (barang_status){
            return new ResponseEntity<>(barang_status, HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(barang_status, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/barang/update/{userId}/{barangId}")
    public ResponseEntity<?> update_barang(@PathVariable("barangId") int barangId,@PathVariable("userId") int userId, @RequestParam ("barangImg") MultipartFile fileUpload, BarangDto barangDto)throws  IOException{
        barangDto.setBarangImg(fileUpload);
        barangService.edit_barang(barangId, userId, barangDto);
        Barang response = barangService.display_barang_byId(barangId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}
