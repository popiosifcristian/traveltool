package travel.tool.epo;

/**
 * @author ipop
 */
public class LandmarkEpo {
    private long id;
    private String name;
    private String location;
    private String description;

    public LandmarkEpo(long id, String name, String location, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    private LandmarkEpo(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
