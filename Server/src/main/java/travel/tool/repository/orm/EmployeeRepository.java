package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.tool.model.Employee;
import travel.tool.repository.IEmployeeRepository;

/**
 * @author ipop
 */
@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long>, IEmployeeRepository {
}
