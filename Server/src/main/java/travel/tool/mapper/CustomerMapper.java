package travel.tool.mapper;

import org.springframework.stereotype.Service;
import travel.tool.epo.CustomerEpo;
import travel.tool.model.Customer;

/**
 * @author ipop
 */
@Service
public class CustomerMapper extends GenericMapper<Customer, CustomerEpo> {
    @Override
    public Customer toInternal(CustomerEpo epo) {
        return new Customer(epo.getId(), epo.getFirstName(), epo.getLastName(), epo.getEmail(), epo.getPhoneNumber());
    }

    @Override
    public CustomerEpo toExternal(Customer model) {
        return new CustomerEpo(model.getId(), model.getFirstName(), model.getLastName(), model.getEmail(), model.getPhoneNumber());
    }
}
