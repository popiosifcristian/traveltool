package travel.tool.epo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author ipop
 */
@Getter
@AllArgsConstructor
public class CustomerEpo {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private CustomerEpo() {}
}
