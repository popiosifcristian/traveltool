package travel.tool.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ipop
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = -8863250924073510965L;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company")
    private Company agency;

    public Employee(String username, String password, String email, String firstName, String lastName,
                    String phoneNumber, Company agency) {
        super(0L);
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
