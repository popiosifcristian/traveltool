package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import travel.tool.epo.EmployeeEpo;
import travel.tool.mapper.EmployeeMapper;
import travel.tool.model.Employee;
import travel.tool.service.EmployeeService;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin()
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<EmployeeEpo> findAll() {
        return employeeMapper.toExternal(employeeService.findAll());
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public EmployeeEpo getOne(@RequestParam Long id) {
        return employeeMapper.toExternal(employeeService.getOne(id));
    }

    @PostMapping(value = "/save")
    public EmployeeEpo save(@RequestBody EmployeeEpo model) {
        employeeService.save(employeeMapper.toInternal(model));
        return model;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody EmployeeEpo model) {
        employeeService.delete(employeeMapper.toInternal(model));
    }
}
