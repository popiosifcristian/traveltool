package travel.tool.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import travel.tool.entity.Customer;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ipop
 */
public class JdbcTemplateCustomer implements ICustomerBaseRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateCustomer(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private String getAllQuery = "SELECT c.id, c.first_name, c.last_name, c.email, c.phone_number FROM customer c ";
    private String findByIdQuery = getAllQuery + "WHERE c.id=?";
    private String deleteByIdQuery = "DELETE FROM customer WHERE id=?";

    @Override
    public Collection<Customer> getAll() {
        String query = getAllQuery;
        Collection<Customer> customers = jdbcTemplate.query(query, new CustomerResultSetExtractor());
        return customers;
    }

    @Override
    public Customer findById(Long id) {
        Collection<Customer> customers = jdbcTemplate.query(findByIdQuery, new CustomerResultSetExtractor(), id);
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
        String sql;
        Long newId;
        if (customer.getId() > 0) {
            sql = "UPDATE customer SET first_name=?, last_name=?, email=?, phone_number=? WHERE id=? returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getId()
            }, (resultSet, i) -> resultSet.getLong(1));
        } else {
            sql = "INSERT INTO customer (first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?) returning id";
            newId = jdbcTemplate.queryForObject(sql, new Object[]{
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
        return jdbcTemplate.update(deleteByIdQuery, customer.getId()) > 0;
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
