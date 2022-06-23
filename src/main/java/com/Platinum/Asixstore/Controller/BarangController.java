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

    @PostMapping("/barang/daftar")
    public ResponseEntity<?> submit_barang(BarangDto barangDto, @RequestParam("barangImg") MultipartFile fileUpload) throws IOException {
        barangDto.setBarangImg(fileUpload);
        Barang barang = barangService.submit_barang(barangDto);
        return new ResponseEntity<>(barang, HttpStatus.CREATED);
    }

    @GetMapping("/barang")
    public ResponseEntity<?> display_barang() {
        List<Barang> barangs = barangService.display_barang();
        return new ResponseEntity<>(barangs, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/barang/{tipeBarang}", method=RequestMethod.GET)
    public ResponseEntity<?> filter_barang(@PathVariable("tipeBarang") String tipeBarang)throws Exception{

        List<Barang> barangFilter = barangService.filter_barang(tipeBarang);
        return new ResponseEntity<>(barangFilter, HttpStatus.ACCEPTED);

    }

    @PutMapping("/barang/edit/{barangId}")
    public ResponseEntity<?> update_barang(@PathVariable("barangId") int barangId, @RequestParam ("barangImg") MultipartFile fileUpload, BarangDto barangDto)throws  IOException{
        barangDto.setBarangImg(fileUpload);
        barangService.edit_barang(barangId, barangDto);
        Barang response = barangService.display_barang_byId(barangId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);


    }
}
