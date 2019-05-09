package travel.tool.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ipop
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "landmark")
public class Landmark extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = -3371897594626639687L;
    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "description")
    private String description;

    public Landmark(String name, String location, String description) {
        super(0L);
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
