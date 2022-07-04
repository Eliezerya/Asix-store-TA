package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface StatusRepo extends JpaRepository<Status, Integer> {
    List<Status>  findByStatusId(int statusId);
    Status findByStatusId(Integer statusId);
}
