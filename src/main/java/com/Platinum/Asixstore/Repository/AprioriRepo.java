package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.Apriori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AprioriRepo extends JpaRepository<Apriori, Integer> {

    @Query(value = "select * from apriori a where barang =:barang",nativeQuery = true)
    Apriori findByBarang(@Param("barang") String barang);
    Apriori findByRekomendasi(String barang);
    List<Apriori> findAllByBarang(String barang);

    List<Apriori> findAllByRekomendasi(String barang);

    Apriori findByBarangAndRekomendasi(String barang, String rekomendasi);
}
