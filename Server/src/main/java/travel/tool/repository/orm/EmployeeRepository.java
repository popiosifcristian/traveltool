package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import travel.tool.model.Employee;
import travel.tool.repository.IEmployeeRepository;

/**
 * @author ipop
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>, IEmployeeRepository {
}
