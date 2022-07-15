package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.BarangBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BarangBuyerRepo extends JpaRepository<BarangBuyer, Integer> {
    @Modifying
    @Query(value = "delete from barang_buyer bb where bb.barang_id =:barangId", nativeQuery = true)
    public void deleteNative(@Param("barangId")int barangId);

}
