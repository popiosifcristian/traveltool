package travel.tool.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import travel.tool.entity.Employee;
import travel.tool.repository.ICompanyRepository;
import travel.tool.repository.IEmployeeRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static travel.tool.util.TravelToolConstants.*;

/**
 * @author ipop
 */
public class JdbcTemplateEmployee implements IEmployeeRepository {
    @Autowired
    private ICompanyRepository companyRepository;
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateEmployee(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Employee> getAll() {
        return jdbcTemplate.query(EMPLOYEE_GET_ALL, new EmployeeResultSetExtractor());
    }

    @Override
    public Employee findById(long id) {
        Collection<Employee> employees = jdbcTemplate.query(EMPLOYEE_FIND_BY_ID, new EmployeeResultSetExtractor(), id);
        Employee employee;
        if (employees.size() != 1) {
            employee = null;
        } else {
            employee = employees.iterator().next();
        }
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        Long newId;
        if (employee.getId() > 0) {
            newId = jdbcTemplate.queryForObject(EMPLOYEE_UPDATE, new Object[]{
                    employee.getUsername(),
                    employee.getPassword(),
                    employee.getEmail(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getPhoneNumber(),
                    employee.getAgency().getId(),
                    employee.getId()
            }, (resultSet, i) -> resultSet.getLong(1));
        } else {
            newId = jdbcTemplate.queryForObject(EMPLOYEE_SAVE, new Object[]{
                    employee.getUsername(),
                    employee.getPassword(),
                    employee.getEmail(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getPhoneNumber(),
                    employee.getAgency().getId(),
            }, (resultSet, i) -> resultSet.getLong(1));
        }
        employee.setId(newId);
        return employee;
    }

    @Override
    public boolean delete(Employee employee) {
        return jdbcTemplate.update(EMPLOYEE_DELETE_BY_ID, employee.getId()) > 0;
    }

    @Override
    public Employee findByUsername(String username) {
        Collection<Employee> employees = jdbcTemplate.query(EMPLOYEE_FIND_BY_USERNAME, new EmployeeResultSetExtractor(), username);
        Employee employee;
        if (employees.size() != 1) {
            employee = null;
        } else {
            employee = employees.iterator().next();
        }
        return employee;
    }

    private class EmployeeResultSetExtractor implements ResultSetExtractor<Collection<Employee>> {
        @Override
        public Collection<Employee> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Long, Employee> employeeMap = new HashMap<>();

            while (resultSet.next()) {
                if (!employeeMap.keySet().contains(resultSet.getLong("id"))) {
                    Employee employee = new Employee();
                    employee.setId(resultSet.getLong("id"));
                    employee.setUsername(resultSet.getString("username"));
                    employee.setPassword(resultSet.getString("password"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setFirstName(resultSet.getString("first_name"));
                    employee.setLastName(resultSet.getString("last_name"));
                    employee.setPhoneNumber(resultSet.getString("phone_number"));
                    employee.setAgency(companyRepository.findById(resultSet.getLong("company")));

                    employeeMap.put(employee.getId(), employee);
                }
            }
            return employeeMap.values();
        }
    }
}
