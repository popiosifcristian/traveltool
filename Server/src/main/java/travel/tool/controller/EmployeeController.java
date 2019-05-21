package travel.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.tool.model.Employee;
import travel.tool.service.EmployeeService;

import java.util.Collection;

/**
 * @author ipop
 */
@RestController
@CrossOrigin(origins = "http://localhost:2121/employee")
@RequestMapping(value = "/employee")
public class EmployeeController implements ICrudController<Employee> {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }

    @Override
    public Employee getOne(long id) {
        return employeeService.getOne(id);
    }

    @Override
    public Employee save(Employee model) {
        return employeeService.save(model);
    }

    @Override
    public void delete(Employee model) {
        employeeService.delete(model);
    }
}
