package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.tool.repository.ICompanyRepository;

/**
 * @author ipop
 */
@Service
public class CompanyService {
    @Autowired
    private ICompanyRepository companyRepository;
}
