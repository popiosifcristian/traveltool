package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.tool.entity.Company;
import travel.tool.repository.ICompanyRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ipop
 */
@Service
public class CompanyService implements ICompanyRepository {
    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    public List<Company> getAll() {
        return new ArrayList<>(companyRepository.getAll());
    }

    @Override
    public Company findById(long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company update(Company company) {
        return companyRepository.update(company);
    }

    @Override
    public boolean delete(Company company) {
        return companyRepository.delete(company);
    }
}
