package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import travel.tool.model.Company;
import travel.tool.repository.ICompanyRepository;

/**
 * @author ipop
 */
public interface CompanyRepository extends JpaRepository<Company, Long>, ICompanyRepository {
}
