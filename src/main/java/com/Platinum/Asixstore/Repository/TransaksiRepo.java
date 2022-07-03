package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.Barang;
import com.Platinum.Asixstore.Entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface TransaksiRepo extends JpaRepository<Transaksi, Integer> {
//    Transaksi findByBarang(int barangId);
}
