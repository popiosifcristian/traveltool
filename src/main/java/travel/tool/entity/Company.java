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
public class Company extends AbstractEntity{
    private String name;
    private String address;
    private String website;
    private String email;
    private String phoneNumber;
    private String description;
    private CompanyType companyType;

    public Company(String name, String address, String website, String email, String phoneNumber, String description,
                   CompanyType companyType) {
        this.id = 0;
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
