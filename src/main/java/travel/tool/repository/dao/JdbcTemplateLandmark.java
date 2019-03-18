package travel.tool.repository.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import travel.tool.entity.Landmark;
import travel.tool.repository.ILandmarkRepository;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * @author ipop
 */
public class JdbcTemplateLandmark implements ILandmarkRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateLandmark(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Landmark> getAll() {
        return null;
    }

    @Override
    public Landmark findById(long id) {
        return null;
    }

    @Override
    public Landmark update(Landmark model) {
        return null;
    }

    @Override
    public boolean delete(Landmark model) {
        return false;
    }
}
