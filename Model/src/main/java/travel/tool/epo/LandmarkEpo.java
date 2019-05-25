package travel.tool.epo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ipop
 */
@Getter
@AllArgsConstructor
public class LandmarkEpo {
    private long id;
    private String name;
    private String location;
    private String description;

    private LandmarkEpo(){}
}
