package travel.tool.epo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import travel.tool.model.CompanyType;

/**
 * @author ipop
 */
@Getter
@AllArgsConstructor
public class CompanyEpo {
    private Long id;
    private String name;
    private String address;
    private String website;
    private String email;
    private String phoneNumber;
    private String description;
    private CompanyType companyType;

    private CompanyEpo(){}
}
