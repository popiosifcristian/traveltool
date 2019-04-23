package travel.tool.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ipop
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request implements Serializable {
    private Object data;
    private Type type;

    public Request(Type type) {
        this.type = type;
    }
}
