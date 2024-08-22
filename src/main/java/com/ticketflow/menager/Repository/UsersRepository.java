package com.ticketflow.menager.Repository;

import com.ticketflow.menager.Entity.Users; // Certifique-se de que esta é a importação correta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
