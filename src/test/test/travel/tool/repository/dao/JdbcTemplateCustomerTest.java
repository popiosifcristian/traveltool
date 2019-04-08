package travel.tool.repository.dao;

import org.junit.jupiter.api.BeforeAll;
import travel.tool.entity.Customer;

/**
 * @author ipop
 */
class JdbcTemplateCustomerTest extends JdbcTemplateTest<Customer> {

    @BeforeAll
    void initializeUnitUnderTest() {
        unitUnderTest = new JdbcTemplateCustomer(configuration.dataSource());
    }

    protected Customer generateTestData() {
        return new Customer("First Name", "Last Name", "Email", "Phone number");
    }

    protected Customer generateTestData(long id) {
        return new Customer(id, "First Name", "Last Name", "Email", "Phone number");
    }

    protected Customer generateTestUpdatedData() {
        return new Customer(actualId, "First Name", "Last Name", "Email", "Phone number");
    }
}