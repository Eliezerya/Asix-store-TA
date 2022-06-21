package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BarangRepo extends JpaRepository<Barang, Integer> {
    Barang findByBarangId(int barangId);
    List<Barang> findAll();
    List<Barang> findByTipeBarang(String tipebarang);
}
