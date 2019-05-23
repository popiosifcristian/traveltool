package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.tool.model.Company;
import travel.tool.service.CompanyService;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin(origins = "http://localhost:2121/company")
@RequestMapping(value = "/company")
public class CompanyController implements ICrudController<Company> {
    @Autowired
    private CompanyService companyService;

    @Override
    public Collection<Company> findAll() {
        return companyService.findAll();
    }

    @Override
    public Company getOne(Long id) {
        return companyService.getOne(id);
    }

    @Override
    public Company save(Company model) {
        return companyService.save(model);
    }

    @Override
    public void delete(Company model) {
        companyService.delete(model);
    }
}
