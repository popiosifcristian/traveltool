package travel.tool.entity;

import lombok.*;

/**
 * @author ipop
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company{
    private long id;
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
}
