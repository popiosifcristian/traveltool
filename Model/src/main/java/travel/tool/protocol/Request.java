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
    private static final long serialVersionUID = -2011766021764815724L;
    private Object data;
    private Type type;

    public Request(Type type) {
        this.type = type;
    }
}
