package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.ViewNotifikasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ViewNotifikasiRepo extends JpaRepository<ViewNotifikasi, Integer> {
    List<ViewNotifikasi> findByUserIdSellerAndStatusBarang(int userIdSeller, String statusBarang);

    List<ViewNotifikasi> findByUserIdBuyerAndStatusBarang(int userIdBuyer, String statusBarang);
    List<ViewNotifikasi> findByBarangId(int barangId);

}
