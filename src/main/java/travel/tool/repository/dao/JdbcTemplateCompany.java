package travel.tool.repository.dao;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import travel.tool.entity.Company;
import travel.tool.entity.CompanyType;
import travel.tool.repository.ICompanyRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static travel.tool.util.TravelToolConstants.*;

/**
 * @author ipop
 */
@Component
public class JdbcTemplateCompany implements ICompanyRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateCompany(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Company> getAll() {
        return jdbcTemplate.query(COMPANY_GET_ALL, new CompanyResultSetExtractor());
    }

    @Override
    public Company findById(long id) {
        Collection<Company> companies = jdbcTemplate.query(COMPANY_FIND_BY_ID, new CompanyResultSetExtractor(), id);
        Company company;
        if (companies.size() != 1) {
            company = null;
        } else {
            company = companies.iterator().next();
        }
        return company;
    }

    @Override
    public Company update(Company company) {
        Long newId;
        if (company.getId() > 0) {
            newId = jdbcTemplate.queryForObject(COMPANY_UPDATE, new Object[]{
                    company.getName(),
                    company.getAddress(),
                    company.getWebsite(),
                    company.getEmail(),
                    company.getPhoneNumber(),
                    company.getDescription(),
                    company.getCompanyType().name(),
                    company.getId()
            }, (resultSet, i) -> resultSet.getLong(1));
        } else {
            newId = jdbcTemplate.queryForObject(COMPANY_SAVE, new Object[]{
                    company.getName(),
                    company.getAddress(),
                    company.getWebsite(),
                    company.getEmail(),
                    company.getPhoneNumber(),
                    company.getDescription(),
                    company.getCompanyType().name(),
            }, (resultSet, i) -> resultSet.getLong(1));
        }
        company.setId(newId);
        return company;
    }

    @Override
    public boolean delete(Company company) {
        return jdbcTemplate.update(COMPANY_DELETE_BY_ID, company.getId()) > 0;
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
