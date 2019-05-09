package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.tool.model.Company;
import travel.tool.repository.ICompanyRepository;

/**
 * @author ipop
 */
@Repository("companyRepository")
public interface CompanyRepository extends JpaRepository<Company, Long>, ICompanyRepository {
}
