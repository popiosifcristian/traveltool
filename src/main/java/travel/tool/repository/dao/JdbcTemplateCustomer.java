package travel.tool.repository.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import travel.tool.entity.Customer;
import travel.tool.repository.ICustomerRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static travel.tool.util.TravelToolConstants.*;

/**
 * @author ipop
 */
public class JdbcTemplateCustomer implements ICustomerRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateCustomer(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Customer> getAll() {
        return jdbcTemplate.query(CUSTOMER_GET_ALL, new CustomerResultSetExtractor());
    }

    @Override
    public Customer findById(long id) {
        Collection<Customer> customers = jdbcTemplate.query(CUSTOMER_FIND_BY_ID, new CustomerResultSetExtractor(), id);
        Customer customer;
        if (customers.size() != 1) {
            customer = null;
        } else {
            customer = customers.iterator().next();
        }
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        Long newId;
        if (customer.getId() > 0) {
            newId = jdbcTemplate.queryForObject(CUSTOMER_UPDATE, new Object[]{
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getId()
            }, (resultSet, i) -> resultSet.getLong(1));
        } else {
            newId = jdbcTemplate.queryForObject(CUSTOMER_SAVE, new Object[]{
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
            }, (resultSet, i) -> resultSet.getLong(1));
        }
        customer.setId(newId);
        return customer;
    }

    @Override
    public boolean delete(Customer customer) {
        return jdbcTemplate.update(CUSTOMER_DELETE_BY_ID, customer.getId()) > 0;
    }

    private static class CustomerResultSetExtractor implements ResultSetExtractor<Collection<Customer>> {
        @Override
        public Collection<Customer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Long, Customer> customerMap = new HashMap<>();

            while (resultSet.next()) {
                if (!customerMap.keySet().contains(resultSet.getLong("id"))) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getLong("id"));
                    customer.setFirstName(resultSet.getString("first_name"));
                    customer.setLastName(resultSet.getString("last_name"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setPhoneNumber(resultSet.getString("phone_number"));

                    customerMap.put(customer.getId(), customer);
                }
            }
            return customerMap.values();
        }
    }
}
