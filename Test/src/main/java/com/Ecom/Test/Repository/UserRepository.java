package com.Ecom.Test.Repository;

import com.Ecom.Test.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByEmail(String email);
}
