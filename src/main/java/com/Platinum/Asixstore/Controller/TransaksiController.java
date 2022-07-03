package com.Platinum.Asixstore.Controller;

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

    @GetMapping("/transaksi/{userId}/{barangId}")
    public ResponseEntity<?> transaksi_seller_buyer(@PathVariable("barangId") int barangId,@PathVariable("userId") int userId){
        transaksiService.transaksi(barangId, userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
