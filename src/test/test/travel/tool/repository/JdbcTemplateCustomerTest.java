package travel.tool.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import travel.tool.ApplicationConfiguration;
import travel.tool.entity.Customer;
import travel.tool.repository.dao.JdbcTemplateCustomer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author ipop
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class JdbcTemplateCustomerTest {
    private ApplicationConfiguration configuration = new ApplicationConfiguration();
    private JdbcTemplateCustomer unitUnderTest = new JdbcTemplateCustomer(configuration.dataSource());
    private static long actualId = 0;

    @Test//update_save
    void test_1() {
        actualId = unitUnderTest.update(generateTestCustomer()).getId();
        Assertions.assertNotEquals(0, actualId);
    }

    @Test//findById
    void test_2() {
        assertEquals(generateTestCustomer(actualId), unitUnderTest.findById(actualId));
    }

    @Test//update
    void test_3() {
        unitUnderTest.update(generateTestUpdatedCustomer());
        assertEquals(generateTestUpdatedCustomer(), unitUnderTest.findById(actualId));
    }

    @Test//delete
    void test_4() {
        assertTrue(unitUnderTest.delete(generateTestUpdatedCustomer()));
    }


    private Customer generateTestCustomer() {
        return new Customer("First Name", "Last Name", "Email", "Phone number");
    }

    private Customer generateTestCustomer(long id) {
        return new Customer(id, "First Name", "Last Name", "Email", "Phone number");
    }


    private Customer generateTestUpdatedCustomer() {
        return new Customer(actualId, "First Name", "Last Name", "Email", "Phone number");
    }
}