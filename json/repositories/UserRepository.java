package com.softuni.json.repositories;

import com.softuni.json.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
}
