package travel.tool.entity;

import lombok.*;

/**
 * @author ipop
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Landmark {
    private long id;
    private String name;
    private String location;
    private String description;

    public Landmark(String name, String location, String description) {
        this.id = 0;
        this.name = name;
        this.location = location;
        this.description = description;
    }
}
