package travel.tool.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ipop
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Employee extends AbstractEntity{
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Company agency;

    public Employee(String username, String password, String email, String firstName, String lastName,
                    String phoneNumber, Company agency) {
        this.id = 0;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.agency = agency;
    }

    public Employee(long id, String username, String password, String email, String firstName, String lastName,
                    String phoneNumber, Company agency) {
        super(id);
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.agency = agency;
    }
}
