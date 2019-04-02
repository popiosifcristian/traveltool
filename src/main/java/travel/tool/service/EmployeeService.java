package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.tool.entity.Employee;
import travel.tool.repository.IEmployeeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ipop
 */
@Service
public class EmployeeService {
    @Autowired
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
}
