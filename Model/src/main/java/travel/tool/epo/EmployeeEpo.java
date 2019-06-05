package travel.tool.epo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ipop
 */
@Getter
@AllArgsConstructor
public class EmployeeEpo {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private CompanyEpo agency;

    private EmployeeEpo(){}
}
