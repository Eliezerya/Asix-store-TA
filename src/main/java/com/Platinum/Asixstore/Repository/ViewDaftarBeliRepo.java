package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.ViewDaftarBeli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ViewDaftarBeliRepo extends JpaRepository<ViewDaftarBeli, Integer> {
    List<ViewDaftarBeli> findByUserIdBuyerAndStatusBarang(int userIdBuyer, String statusBarang);
}
