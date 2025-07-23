package mz.gov.inage.esircev.v2.repositories;

import mz.gov.inage.esircev.v2.entities.Citizen;
import org.springframework.data.repository.CrudRepository;

public interface CitizenRepository extends CrudRepository<Citizen, Long> {
}
