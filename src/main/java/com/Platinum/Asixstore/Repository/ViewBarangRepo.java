package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Entity.ViewBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface ViewBarangRepo extends JpaRepository<ViewBarang, Integer> {
    List<ViewBarang> findAll();
    List<ViewBarang> findByTipeBarang(String tipebarang);
    List<ViewBarang> findByStatusId(int statusId);
    List<ViewBarang> findByUserIdAndStatusId(int userId, int statusId);
    ViewBarang findByBarangId(int barangId);

}
