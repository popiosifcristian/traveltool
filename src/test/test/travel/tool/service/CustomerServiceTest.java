package travel.tool.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import travel.tool.ApplicationConfiguration;
import travel.tool.ApplicationConfigurationTest;
import travel.tool.entity.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author ipop
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfigurationTest.class })
class CustomerServiceTest {
    @Autowired
    private CustomerService unitUnderTest;
    private static long actualId = 0;
    private static int initialSize;

    //update_save
    @Test
    void test_1() {
        initialSize = unitUnderTest.getAll().size();
        actualId = unitUnderTest.update(generateTestData()).getId();
        assertNotEquals(0, actualId);
    }

    //findById
    @Test
    void test_2() {
        assertEquals(generateTestData(actualId), unitUnderTest.findById(actualId));
    }

    //update
    @Test
    void test_3() {
        unitUnderTest.update(generateTestUpdatedData());
        assertEquals(generateTestUpdatedData(), unitUnderTest.findById(actualId));
    }

    //delete
    @Test
    void test_4() {
        assertTrue(unitUnderTest.delete(generateTestUpdatedData()));
        assertEquals(initialSize, unitUnderTest.getAll().size());
    }

    private Customer generateTestData() {
        return new Customer("First Name", "Last Name", "Email", "Phone number");
    }

    private Customer generateTestData(long id) {
        return new Customer(id, "First Name", "Last Name", "Email", "Phone number");
    }

    private Customer generateTestUpdatedData() {
        return new Customer(actualId, "First Name", "Last Name", "Email", "Phone number");
    }
}