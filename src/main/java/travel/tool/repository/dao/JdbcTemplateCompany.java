package travel.tool.repository.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import travel.tool.entity.Company;
import travel.tool.entity.CompanyType;
import travel.tool.repository.ICompanyRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ipop
 */
public class JdbcTemplateCompany implements ICompanyRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateCompany(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Company> getAll() {
        return null;
    }

    @Override
    public Company findById(long id) {
        return null;
    }

    @Override
    public Company update(Company model) {
        return null;
    }

    @Override
    public boolean delete(Company model) {
        return false;
    }


    private static class CompanyResultSetExtractor implements ResultSetExtractor<Collection<Company>> {
        @Override
        public Collection<Company> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Long, Company> companyMap = new HashMap<>();

            while (resultSet.next()) {
                if (!companyMap.keySet().contains(resultSet.getLong("id"))) {
                    Company company = new Company();
                    company.setId(resultSet.getLong("id"));
                    company.setName(resultSet.getString("name"));
                    company.setAddress(resultSet.getString("address"));
                    company.setWebsite(resultSet.getString("website"));
                    company.setEmail(resultSet.getString("email"));
                    company.setPhoneNumber(resultSet.getString("phone_number"));
                    company.setDescription(resultSet.getString("description"));
                    company.setCompanyType(CompanyType.valueOf(resultSet.getString("type")));

                    companyMap.put(company.getId(), company);
                }
            }
            return companyMap.values();
        }
    }
}
