package com.Platinum.Asixstore.Repository;

import com.Platinum.Asixstore.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    List<Role> findByIdRole(int idRole);
}
