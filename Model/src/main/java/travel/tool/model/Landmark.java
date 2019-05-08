package travel.tool.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ipop
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Landmark extends AbstractEntity implements Serializable {
    private String name;
    private String location;
    private String description;

    public Landmark(String name, String location, String description) {
        this.id = 0;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public Landmark(long id, String name, String location, String description) {
        super(id);
        this.name = name;
        this.location = location;
        this.description = description;
    }
}
