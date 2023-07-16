package com.Ecom.Test.Repository;

import com.Ecom.Test.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

    Roles findByName(String name);
}
