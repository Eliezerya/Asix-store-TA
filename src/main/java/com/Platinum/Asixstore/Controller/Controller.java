package com.Platinum.Asixstore.Controller;

import com.Platinum.Asixstore.Dto.BarangDto;
import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    BarangService barangService;

    @PostMapping("/submit/barang")
    public ResponseEntity<?> submit_controller(@RequestBody BarangDto barangDto){
        Barang barang = barangService.submit_barang(barangDto);
        return new ResponseEntity<>(barang, HttpStatus.CREATED);
    }

}
