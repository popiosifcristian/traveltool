package travel.tool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ipop
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Customer(String firstName, String lastName, String email, String phoneNumber) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
