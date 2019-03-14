package travel.tool.repository.crud;

import org.springframework.data.repository.CrudRepository;
import travel.tool.entity.Customer;

/**
 * @author ipop
 */
public interface ICustomerCrudRepository extends CrudRepository<Customer, Long> {
}
