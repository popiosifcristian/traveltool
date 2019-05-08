package travel.tool.repository;

import travel.tool.model.Employee;

/**
 * @author ipop
 */
public interface IEmployeeRepository extends ICrudRepository<Employee> {
    Employee findByUsername(String username);
}
