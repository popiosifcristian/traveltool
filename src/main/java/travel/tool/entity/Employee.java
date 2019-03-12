package travel.tool.entity;

import lombok.*;

/**
 * @author ipop
 */
@Data
public class Employee extends User {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Company agency;

}
