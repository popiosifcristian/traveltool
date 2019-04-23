package travel.tool.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ipop
 */
@Data
@NoArgsConstructor
public class AbstractEntity implements Serializable {
    protected long id;

    AbstractEntity(long id) {
        this.id = id;
    }
}
