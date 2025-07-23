package mz.gov.inage.esircev.v2.repositories;

import mz.gov.inage.esircev.v2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
