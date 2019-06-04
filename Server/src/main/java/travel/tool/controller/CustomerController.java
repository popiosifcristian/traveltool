package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import travel.tool.epo.CustomerEpo;
import travel.tool.mapper.CustomerMapper;
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
    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<CustomerEpo> findAll() {
        return customerMapper.toExternal(customerService.findAll());
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public CustomerEpo getOne(@RequestParam Long id) {
        return customerMapper.toExternal(customerService.getOne(id));
    }

    @PostMapping(value = "/save")
    public CustomerEpo save(@RequestBody CustomerEpo model) {
        return customerMapper.toExternal(customerService.save(customerMapper.toInternal(model)));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id) {
        customerService.delete(customerService.getOne(id));
    }
}
