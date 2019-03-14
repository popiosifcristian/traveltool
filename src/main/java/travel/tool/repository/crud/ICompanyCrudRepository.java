package travel.tool.repository.crud;

import org.springframework.data.repository.CrudRepository;
import travel.tool.entity.Company;

/**
 * @author ipop
 */
public interface ICompanyCrudRepository extends CrudRepository<Company, Long> {
}
