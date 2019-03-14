package travel.tool.repository.crud;

import org.springframework.data.repository.CrudRepository;
import travel.tool.entity.Employee;

/**
 * @author ipop
 */
public interface IEmployeeCrudRepository extends CrudRepository<Employee, Long> {
}
