package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.tool.model.Customer;
import travel.tool.service.CustomerService;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin(origins = "http://localhost:2121/customer")
@RequestMapping(value = "/customer")
public class CustomerController implements ICrudController<Customer> {
    @Autowired
    private CustomerService customerService;

    @Override
    public Collection<Customer> findAll() {
        return customerService.findAll();
    }

    @Override
    public Customer getOne(Long id) {
        return customerService.getOne(id);
    }

    @Override
    public Customer save(Customer model) {
        return customerService.save(model);
    }

    @Override
    public void delete(Customer model) {
        customerService.delete(model);
    }
}
