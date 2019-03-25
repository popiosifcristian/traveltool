package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.tool.repository.IEmployeeRepository;

/**
 * @author ipop
 */
@Service
public class EmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;
}
