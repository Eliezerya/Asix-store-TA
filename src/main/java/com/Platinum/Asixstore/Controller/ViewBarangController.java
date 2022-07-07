package com.Platinum.Asixstore.Controller;


import com.Platinum.Asixstore.Repository.BarangRepo;
import com.Platinum.Asixstore.Service.ViewBarangService;
import com.Platinum.Asixstore.Service.ViewDaftarBeliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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
        return new ResponseEntity<>(viewBarangService.view_barang_bysellerandstatus(userId,statusId), HttpStatus.ACCEPTED);
    }


}
