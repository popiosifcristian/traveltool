package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import travel.tool.model.Customer;
import travel.tool.service.CustomerService;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin()
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<Customer> findAll() {
        return customerService.findAll();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Customer getOne(@RequestParam Long id) {
        return customerService.getOne(id);
    }

    @PostMapping(value = "/save")
    public Customer save(@RequestBody Customer model) {
        return customerService.save(model);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody Customer model) {
        customerService.delete(model);
    }
}
