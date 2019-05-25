package travel.tool.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import travel.tool.epo.CompanyEpo;
import travel.tool.model.CompanyType;

/**
 * @author ipop
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompanyControllerTest extends AbstractRestControllerTest<CompanyEpo> {
    @Override
    public CompanyEpo generateTestData() {
        return new CompanyEpo(actualId, "Company", "Address", "Website", "Email",
                "PhoneNumber", "Description", CompanyType.AGENCY);
    }

    @BeforeAll
    public void initializeEndpointName() {
        this.endpointName = "company";
    }
}
