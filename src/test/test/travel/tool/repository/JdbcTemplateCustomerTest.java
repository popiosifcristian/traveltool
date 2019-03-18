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
    private static int initialSize;

    //update_save
    @Test
    void test_1() {
        actualId = unitUnderTest.update(generateTestCustomer()).getId();
        initialSize = unitUnderTest.getAll().size();
        Assertions.assertNotEquals(0, actualId);
    }

    //findById
    @Test
    void test_2() {
        assertEquals(generateTestCustomer(actualId), unitUnderTest.findById(actualId));
    }

    //update
    @Test
    void test_3() {
        unitUnderTest.update(generateTestUpdatedCustomer());
        assertEquals(generateTestUpdatedCustomer(), unitUnderTest.findById(actualId));
    }

    //delete
    @Test
    void test_4() {
        assertTrue(unitUnderTest.delete(generateTestUpdatedCustomer()));
        assertEquals(initialSize, unitUnderTest.getAll().size());
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