package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.ViewBarang;
import com.Platinum.Asixstore.Entity.ViewBarangApriori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewBarangAprioriRepo extends JpaRepository<ViewBarangApriori, Integer> {
    List<ViewBarangApriori> findByStatusIdAndTipeBarang( int statusId,String tipe);
}
