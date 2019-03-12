package travel.tool.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import travel.tool.ApplicationConfiguration;
import travel.tool.entity.Customer;

/**
 * @author ipop
 */
class JdbcTemplateCustomerTest {
    private ApplicationConfiguration configuration = new ApplicationConfiguration();
    private JdbcTemplateCustomer unitUnderTest = new JdbcTemplateCustomer(configuration.dataSource());
    private Long actualId = 5l;

    @Test
    void getAll_empty() {
        Assertions.assertTrue(unitUnderTest.getAll().isEmpty());
    }

    @Test
    void update_save() {
        Customer customer = new Customer(0L, "First Name", "Last Name", "Email", "Phone number");
        Assertions.assertEquals((long) actualId, (long) unitUnderTest.update(customer).getId());
    }

    @Test
    void findById() {
        Customer customer = new Customer(actualId, "First Name", "Last Name", "Email", "Phone number");
        Assertions.assertEquals(customer, unitUnderTest.findById(actualId));
    }

    @Test
    void update() {
        Customer customer = new Customer(actualId, "First Customer", "Last Customer", "Email Customer", "Phone number Customer");
        Assertions.assertEquals(customer, unitUnderTest.update(customer));
    }

    @Test
    void delete() {
        Customer customer = new Customer(actualId, "First Customer", "Last Customer", "Email Customer", "Phone number Customer");
        Assertions.assertTrue(unitUnderTest.delete(customer));
    }
}