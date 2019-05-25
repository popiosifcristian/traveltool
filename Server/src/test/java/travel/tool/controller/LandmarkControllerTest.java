package travel.tool.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import travel.tool.epo.LandmarkEpo;

/**
 * @author ipop
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LandmarkControllerTest extends AbstractRestControllerTest<LandmarkEpo> {

    public LandmarkEpo generateTestData() {
        return new LandmarkEpo(actualId, "Name", "Location", "Description");
    }

    @BeforeAll
    public void initializeEndpointName() {
        this.endpointName = "landmark";
    }
}