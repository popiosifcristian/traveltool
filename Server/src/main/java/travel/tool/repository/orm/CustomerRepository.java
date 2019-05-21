package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.tool.model.Customer;
import travel.tool.repository.ICustomerRepository;

/**
 * @author ipop
 */
@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Long>, ICustomerRepository {
}
