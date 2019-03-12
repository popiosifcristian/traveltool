package travel.tool.repository;

import org.springframework.data.repository.CrudRepository;
import travel.tool.entity.Customer;

/**
 * @author ipop
 */
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
}
