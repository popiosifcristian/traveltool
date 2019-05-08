package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import travel.tool.model.Customer;
import travel.tool.repository.ICustomerRepository;

/**
 * @author ipop
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>, ICustomerRepository {
}
