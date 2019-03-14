package travel.tool.repository;

import org.junit.jupiter.api.*;
import travel.tool.ApplicationConfiguration;
import travel.tool.entity.Customer;
import travel.tool.repository.dao.JdbcTemplateCustomer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author ipop
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JdbcTemplateCustomerTest {
    private ApplicationConfiguration configuration = new ApplicationConfiguration();
    private JdbcTemplateCustomer unitUnderTest = new JdbcTemplateCustomer(configuration.dataSource());
    private long actualId;

    @Test
    @Order(1)
    void getAll_empty() {
        assertTrue(unitUnderTest.getAll().isEmpty());
    }

    @Test
    @Order(2)
    void update_save() {
        actualId = unitUnderTest.update(generateTestCustomer()).getId();
        assertFalse(unitUnderTest.getAll().isEmpty());
    }

    @Test
    @Order(3)
    void findById() {
        assertEquals(generateTestCustomer(actualId), unitUnderTest.findById(actualId));
    }

    @Test
    @Order(4)
    void update() {
        unitUnderTest.update(generateTestUpdatedCustomer());
        assertEquals(generateTestUpdatedCustomer(), unitUnderTest.findById(actualId));
    }

    @Test
    @Order(5)
    void delete() {
        assertTrue(unitUnderTest.delete(generateTestUpdatedCustomer()));
        assertTrue(unitUnderTest.getAll().isEmpty());
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