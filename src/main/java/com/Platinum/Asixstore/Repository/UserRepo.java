package com.Platinum.Asixstore.Repository;


import com.Platinum.Asixstore.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {

}
