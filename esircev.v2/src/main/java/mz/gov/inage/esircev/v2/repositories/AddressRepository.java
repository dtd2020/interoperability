package mz.gov.inage.esircev.v2.repositories;

import mz.gov.inage.esircev.v2.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository  extends CrudRepository<Address, Long> {
}
