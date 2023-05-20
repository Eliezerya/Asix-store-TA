package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.RekomendasiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RekomendasiUserRepo extends JpaRepository<RekomendasiUser, Integer> {

}
