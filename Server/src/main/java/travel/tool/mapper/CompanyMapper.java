package travel.tool.mapper;

import org.springframework.stereotype.Service;
import travel.tool.epo.CompanyEpo;
import travel.tool.model.Company;

/**
 * @author ipop
 */
@Service
public class CompanyMapper extends GenericMapper<Company, CompanyEpo> {
    @Override
    public Company toInternal(CompanyEpo epo) {
        return new Company(epo.getId(), epo.getName(), epo.getAddress(), epo.getWebsite(), epo.getEmail(), epo.getPhoneNumber(), epo.getDescription(), epo.getCompanyType());
    }

    @Override
    public CompanyEpo toExternal(Company model) {
        return new CompanyEpo(model.getId(), model.getName(), model.getAddress(), model.getWebsite(), model.getEmail(), model.getPhoneNumber(), model.getDescription(), model.getCompanyType());
    }
}
