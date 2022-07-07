package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.ViewNotifikasiBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional

public interface ViewNotifikasiBuyerRepo extends JpaRepository<ViewNotifikasiBuyer, Integer> {
    List<ViewNotifikasiBuyer> findByUserIdBuyerAndStatusBarang(int userIdBuyer, String statusBarang);
}
