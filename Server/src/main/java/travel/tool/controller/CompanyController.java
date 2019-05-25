package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import travel.tool.model.Company;
import travel.tool.service.CompanyService;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin()
@RequestMapping(value = "/company")
public class CompanyController{
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<Company> findAll() {
        return companyService.findAll();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Company getOne(@RequestParam Long id) {
        return companyService.getOne(id);
    }

    @PostMapping(value = "/save")
    public Company save(@RequestBody Company model) {
        return companyService.save(model);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody Company model) {
        companyService.delete(model);
    }
}
