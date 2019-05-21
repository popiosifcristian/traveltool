package travel.tool.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ipop
 */
@Data
@NoArgsConstructor
@MappedSuperclass
public class AbstractEntity implements Serializable {
    private static final long serialVersionUID = -3661950506658117490L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    AbstractEntity(long id) {
        this.id = id;
    }
}
