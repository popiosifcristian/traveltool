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
@Table(name = "company")
public class Company extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 9160166039239723445L;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "website")
    private String website;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    public Company(String name, String address, String website, String email, String phoneNumber, String description,
                   CompanyType companyType) {
        super(0);
        this.name = name;
        this.address = address;
        this.website = website;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.companyType = companyType;
    }

    public Company(long id, String name, String address, String website, String email, String phoneNumber,
                   String description, CompanyType companyType) {
        super(id);
        this.name = name;
        this.address = address;
        this.website = website;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.companyType = companyType;
    }
}
