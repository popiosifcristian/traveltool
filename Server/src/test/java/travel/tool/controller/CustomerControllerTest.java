package travel.tool.controller;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import travel.tool.epo.CustomerEpo;

/**
 * @author ipop
 */
@Ignore
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTest extends AbstractRestControllerTest<CustomerEpo> {
    @Override
    public CustomerEpo generateTestData() {
        return new CustomerEpo(actualId, "Customer", "Joe", "Email", "12314213");
    }

    @BeforeAll
    public void initializeEndpointName() {
        this.endpointName = "customer";
    }
}
