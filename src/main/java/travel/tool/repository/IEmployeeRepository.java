package travel.tool.repository;

import org.springframework.data.repository.CrudRepository;
import travel.tool.entity.Employee;

/**
 * @author ipop
 */
public interface IEmployeeRepository extends CrudRepository<Employee, Long> {
}
