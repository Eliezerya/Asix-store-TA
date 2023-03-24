package com.Platinum.Asixstore.Controller;


import com.Platinum.Asixstore.Entity.ViewBarang;
import com.Platinum.Asixstore.Repository.BarangRepo;
import com.Platinum.Asixstore.Service.ViewBarangService;
import com.Platinum.Asixstore.Service.ViewDaftarBeliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@CrossOrigin
public class ViewBarangController {
    @Autowired
    BarangRepo barangRepo;
    @Autowired
    ViewBarangService viewBarangService;

    @Autowired
    ViewDaftarBeliService viewDaftarBeliService;

    @GetMapping("/daftar-jual/{statusId}")
    public ResponseEntity<?> display_barang_byStatus(@PathVariable("statusId") int statusId) throws Exception {
        return new ResponseEntity<>(viewBarangService.view_barang_bystatus(statusId), HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/daftar-jual/{userId}/{statusId}", method = RequestMethod.GET)
    public ResponseEntity<?> display_barang_by_SellerandStatus(@PathVariable("userId") int userId,@PathVariable("statusId") int statusId) throws Exception {
        Map<String, Object> map = new HashMap<>();

//        map.put("file_name", );
        return new ResponseEntity<>(viewBarangService.view_barang_bysellerandstatus(userId,statusId), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/detail-barang/{barangId}") //view barang by id
    public ResponseEntity<?> view_barang(@PathVariable(value = "barangId") int barangId) throws Exception{
        ViewBarang viewBarang = viewBarangService.view_barang_by_id(barangId);
        return new ResponseEntity<>(viewBarang, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/barang/{tipeBarang}", method = RequestMethod.GET) // kategori gitar dan aksesoris
    public ResponseEntity<?> filter_barang(@PathVariable("tipeBarang") String tipeBarang) throws Exception {
        List<ViewBarang> barangFilter = viewBarangService.filter_barang(tipeBarang.toUpperCase(Locale.ROOT));
        return new ResponseEntity<>(barangFilter, HttpStatus.ACCEPTED);
    }

    @GetMapping("/barang") //tampilkan semua barang
    public ResponseEntity<?> display_barang() {
        return new ResponseEntity<>(viewBarangService.view_semua_barang(), HttpStatus.ACCEPTED);
    }
}
