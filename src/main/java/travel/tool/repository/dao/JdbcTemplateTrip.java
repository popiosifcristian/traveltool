package travel.tool.repository.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import travel.tool.entity.Trip;
import travel.tool.repository.ITripRepository;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * @author ipop
 */
public class JdbcTemplateTrip implements ITripRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateTrip(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Trip> getAll() {
        return null;
    }

    @Override
    public Trip findById(long id) {
        return null;
    }

    @Override
    public Trip update(Trip model) {
        return null;
    }

    @Override
    public boolean delete(Trip model) {
        return false;
    }
}
