package com.mehdi.jpauserroles.repositories;

import com.mehdi.jpauserroles.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String userName);
}
