package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import travel.tool.entity.Customer;
import travel.tool.repository.ICustomerRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ipop
 */
@Service
@ComponentScan
public class CustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    public List<Customer> getAll() {
        return new ArrayList<>(customerRepository.getAll());
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer update(Customer customer) {
        return customerRepository.update(customer);
    }

    public boolean delete(Customer customer) {
        return customerRepository.delete(customer);
    }
}

