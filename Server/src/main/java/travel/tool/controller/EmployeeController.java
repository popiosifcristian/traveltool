package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Employee getOne(@RequestParam Long id) {
        return employeeService.getOne(id);
    }

    @PostMapping(value = "/save")
    public Employee save(@RequestBody Employee model) {
        return employeeService.save(model);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody Employee model) {
        employeeService.delete(model);
    }
}
