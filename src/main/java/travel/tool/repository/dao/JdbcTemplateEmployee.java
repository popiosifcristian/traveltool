package travel.tool.repository.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import travel.tool.entity.Employee;
import travel.tool.repository.IEmployeeRepository;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * @author ipop
 */
public class JdbcTemplateEmployee implements IEmployeeRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateEmployee(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Employee> getAll() {
        return null;
    }

    @Override
    public Employee findById(long id) {
        return null;
    }

    @Override
    public Employee update(Employee model) {
        return null;
    }

    @Override
    public boolean delete(Employee model) {
        return false;
    }
}
