package mz.gov.inage.esircev.v2.repositories;

import mz.gov.inage.esircev.v2.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
