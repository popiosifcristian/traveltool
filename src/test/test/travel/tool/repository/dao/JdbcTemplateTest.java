package travel.tool.repository.dao;

import org.junit.jupiter.api.*;
import travel.tool.ApplicationConfiguration;
import travel.tool.entity.AbstractEntity;
import travel.tool.repository.ICrudRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author ipop
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public abstract class JdbcTemplateTest<T extends AbstractEntity> {
    ApplicationConfiguration configuration = new ApplicationConfiguration();
    ICrudRepository<T> unitUnderTest;
    static long actualId = 0;
    private static int initialSize;

    //update_save
    @Test
    void test_1() {
        actualId = unitUnderTest.update(generateTestData()).getId();
        initialSize = unitUnderTest.getAll().size();
        Assertions.assertNotEquals(0, actualId);
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

    @BeforeAll
    abstract void initializeUnitUnderTest();

    protected abstract T generateTestData();

    protected abstract T generateTestData(long id);

    protected abstract T generateTestUpdatedData();
}
