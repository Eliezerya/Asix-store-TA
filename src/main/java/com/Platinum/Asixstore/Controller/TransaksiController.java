package com.Platinum.Asixstore.Controller;


import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransaksiController {
    @Autowired
    TransaksiService transaksiService;

    @GetMapping("/barang/notifikasi/{barangId}")
    public ResponseEntity<?> notifikasi_seller (@PathVariable int barangId){
        return new ResponseEntity<>(transaksiService.notifikasi_seller(barangId), HttpStatus.ACCEPTED);

    }

}
