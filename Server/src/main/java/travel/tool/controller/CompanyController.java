package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import travel.tool.epo.CompanyEpo;
import travel.tool.mapper.CompanyMapper;
import travel.tool.service.CompanyService;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin()
@RequestMapping(value = "/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyMapper companyMapper;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<CompanyEpo> findAll() {
        return companyMapper.toExternal(companyService.findAll());
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public CompanyEpo getOne(@RequestParam Long id) {
        return companyMapper.toExternal(companyService.getOne(id));
    }

    @PostMapping(value = "/save")
    public CompanyEpo save(@RequestBody CompanyEpo model) {
        return companyMapper.toExternal(companyService.save(companyMapper.toInternal(model)));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody CompanyEpo model) {
        companyService.delete(companyMapper.toInternal(model));
    }
}
