package mz.gov.inage.esircev.v2.repositories;

import mz.gov.inage.esircev.v2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

}
