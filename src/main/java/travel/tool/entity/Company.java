package travel.tool.entity;

import lombok.*;

/**
 * @author ipop
 */
@Data
public class Company{
    private Long id;
    private String name;
    private String address;
    private String website;
    private String email;
    private String phoneNumber;
    private String description;
    private CompanyType companyType;
}
