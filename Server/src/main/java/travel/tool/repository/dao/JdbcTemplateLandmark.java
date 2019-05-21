package travel.tool.repository.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import travel.tool.model.Landmark;
import travel.tool.repository.ILandmarkRepository;

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
@Repository("jdbcTemplateLandmark")
public class JdbcTemplateLandmark implements ILandmarkRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateLandmark(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Landmark> findAll() {
        return jdbcTemplate.query(LANDMARK_GET_ALL, new LandmarkResultSetExtractor());
    }

    @Override
    public Landmark getOne(long id) {
        Collection<Landmark> landmarks = jdbcTemplate.query(LANDMARK_FIND_BY_ID, new LandmarkResultSetExtractor(), id);
        Landmark landmark;
        if (landmarks.size() != 1) {
            landmark = null;
        } else {
            landmark = landmarks.iterator().next();
        }
        return landmark;    }

    @Override
    public Landmark save(Landmark landmark) {
        Long newId;
        if (landmark.getId() > 0) {
            newId = jdbcTemplate.queryForObject(LANDMARK_UPDATE, new Object[]{
                    landmark.getName(),
                    landmark.getLocation(),
                    landmark.getDescription(),
                    landmark.getId()
            }, (resultSet, i) -> resultSet.getLong(1));
        } else {
            newId = jdbcTemplate.queryForObject(LANDMARK_SAVE, new Object[]{
                    landmark.getName(),
                    landmark.getLocation(),
                    landmark.getDescription(),
            }, (resultSet, i) -> resultSet.getLong(1));
        }
        landmark.setId(newId);
        return landmark;    }

    @Override
    public void delete(Landmark landmark) {
        jdbcTemplate.update(LANDMARK_DELETE_BY_ID, landmark.getId());
    }

    private static class LandmarkResultSetExtractor implements ResultSetExtractor<Collection<Landmark>> {
        @Override
        public Collection<Landmark> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Long, Landmark> landmarkMap = new HashMap<>();

            while (resultSet.next()) {
                if (!landmarkMap.keySet().contains(resultSet.getLong("id"))) {
                    Landmark landmark = new Landmark();
                    landmark.setId(resultSet.getLong("id"));
                    landmark.setName(resultSet.getString("name"));
                    landmark.setLocation(resultSet.getString("location"));
                    landmark.setDescription(resultSet.getString("description"));

                    landmarkMap.put(landmark.getId(), landmark);
                }
            }
            return landmarkMap.values();
        }
    }
}
