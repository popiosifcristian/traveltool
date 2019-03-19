package travel.tool.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ipop
 */
@Data
@NoArgsConstructor
public class AbstractEntity {
    protected long id;

    AbstractEntity(long id) {
        this.id = id;
    }
}
