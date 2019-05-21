package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import travel.tool.model.Company;
import travel.tool.repository.ICompanyRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ipop
 */
@Service
public class CompanyService implements ICompanyRepository {
    @Autowired
    @Qualifier("companyRepository")
    private ICompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return new ArrayList<>(companyRepository.findAll());
    }

    @Override
    public Company getOne(long id) {
        return companyRepository.getOne(id);
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void delete(Company company) {
        companyRepository.delete(company);
    }
}
