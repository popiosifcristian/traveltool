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
public class EmployeeService implements IEmployeeRepository {
    @Autowired
    @Qualifier("employeeRepository")
    private IEmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return new ArrayList<>(employeeRepository.findAll());
    }

    public Employee getOne(long id) {
        return employeeRepository.getOne(id);
    }

    public Employee save(Employee customer) {
        return employeeRepository.save(customer);
    }

    public void delete(Employee customer) {
        employeeRepository.delete(customer);
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
