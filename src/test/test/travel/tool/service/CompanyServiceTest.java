package travel.tool.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import travel.tool.ApplicationConfigurationTest;
import travel.tool.XmlConfigurationTest;
import travel.tool.entity.Company;
import travel.tool.entity.CompanyType;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ipop
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:application_context_test.xml"})
class CompanyServiceTest {
    @Autowired
    private CompanyService unitUnderTest;
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


    private Company generateTestData() {
        return new Company("Name", "Address", "Website", "Email", "Phone number", "Description", CompanyType.AGENCY);
    }


    private Company generateTestData(long actualId) {
        return new Company(actualId,"Name", "Address", "Website", "Email", "Phone number", "Description", CompanyType.AGENCY);
    }

    private Company generateTestUpdatedData() {
        return new Company(actualId,"Name Updated", "Address Updated", "Website Updated", "Email Updated", "Phone number Updated", "Description Updated", CompanyType.TRANSPORT);
    }

}