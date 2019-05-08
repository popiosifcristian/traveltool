package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import travel.tool.model.Employee;
import travel.tool.repository.IEmployeeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ipop
 */
@Service
public class EmployeeService implements IEmployeeRepository{
    @Autowired
    @Qualifier("jdbcTemplateEmployee")
    private IEmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return new ArrayList<>(employeeRepository.getAll());
    }

    public Employee findById(long id) {
        return employeeRepository.findById(id);
    }

    public Employee update(Employee customer) {
        return employeeRepository.update(customer);
    }

    public boolean delete(Employee customer) {
        return employeeRepository.delete(customer);
    }

    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    public boolean authenticate(String username, String password) {
        Employee employee = this.findByUsername(username);
        if (employee == null) {
            return false;
        } else {
            return password.equals(employee.getPassword());
        }
    }
}
