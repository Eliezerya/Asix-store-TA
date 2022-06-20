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

@RestController
public class BarangController {
    @Autowired
    BarangService barangService;

    @PostMapping("/barang/daftar")
    public ResponseEntity<?> submit_controller(BarangDto barangDto, @RequestParam("barangImg") MultipartFile fileUpload)throws IOException {
        barangDto.setBarangImg(fileUpload);
        Barang barang = barangService.submit_barang(barangDto);
        return new ResponseEntity<>(barang, HttpStatus.CREATED);
    }

}
