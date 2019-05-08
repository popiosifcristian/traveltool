package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import travel.tool.model.Customer;
import travel.tool.repository.ICustomerRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ipop
 */
@Service
public class CustomerService implements ICustomerRepository {

    @Autowired
    @Qualifier("jdbcTemplateCustomer")
    private ICustomerRepository customerRepository;

    public List<Customer> getAll() {
        return new ArrayList<>(customerRepository.getAll());
    }

    public Customer findById(long id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
}

