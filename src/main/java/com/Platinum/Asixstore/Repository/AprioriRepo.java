package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.Apriori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AprioriRepo extends JpaRepository<Apriori, Integer> {
    Apriori findByBarang(String barang);
    List<Apriori> findAllByBarang(String barang);

    List<Apriori> findAllByRekomendasi(String barang);

    Apriori findByBarangAndRekomendasi(String barang, String rekomendasi);
}
