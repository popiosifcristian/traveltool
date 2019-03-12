package travel.tool.repository;

import org.springframework.data.repository.CrudRepository;
import travel.tool.entity.Company;

/**
 * @author ipop
 */
public interface ICompanyRepository extends CrudRepository<Company, Long> {
}
