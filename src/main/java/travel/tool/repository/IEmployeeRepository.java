package travel.tool.repository;

import travel.tool.entity.Employee;

/**
 * @author ipop
 */
public interface IEmployeeRepository extends ICrudRepository<Employee> {
    Employee findByUsername(String username);
}
