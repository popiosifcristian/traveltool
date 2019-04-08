package travel.tool.repository.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import travel.tool.entity.Trip;
import travel.tool.repository.ICompanyRepository;
import travel.tool.repository.ILandmarkRepository;
import travel.tool.repository.ITripRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static travel.tool.util.TravelToolConstants.*;

/**
 * @author ipop
 */
public class JdbcTemplateTrip implements ITripRepository {
    @Autowired
    private ICompanyRepository companyRepository;
    @Autowired
    private ILandmarkRepository landmarkRepository;
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateTrip(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Trip> getAll() {
        return jdbcTemplate.query(TRIP_GET_ALL, new TripResultSetExtractor());
    }

    @Override
    public Trip findById(long id) {
        Collection<Trip> trips = jdbcTemplate.query(TRIP_FIND_BY_ID, new TripResultSetExtractor(), id);
        Trip trip;
        if (trips.size() != 1) {
            trip = null;
        } else {
            trip = trips.iterator().next();
        }
        return trip;
    }

    //TODO: FIX THE DATE ISSUES
    @Override
    public Trip update(Trip trip) {
        Long newId;
        if (trip.getId() > 0) {
            newId = jdbcTemplate.queryForObject(TRIP_UPDATE, new Object[]{
                    trip.getLandmark().getId(),
                    trip.getTransportCompany().getId(),
                    trip.getDate(),
                    trip.getStartTime(),
                    trip.getPrice(),
                    trip.getAvailablePlaces(),
                    trip.getId()
            }, (resultSet, i) -> resultSet.getLong(1));
        } else {
            newId = jdbcTemplate.queryForObject(TRIP_SAVE, new Object[]{
                    trip.getLandmark().getId(),
                    trip.getTransportCompany().getId(),
                    trip.getDate(),
                    trip.getStartTime(),
                    trip.getPrice(),
                    trip.getAvailablePlaces(),
            }, (resultSet, i) -> resultSet.getLong(1));
        }
        trip.setId(newId);
        return trip;
    }

    @Override
    public boolean delete(Trip trip) {
        return jdbcTemplate.update(TRIP_DELETE_BY_ID, trip.getId()) > 0;
    }

    private class TripResultSetExtractor implements ResultSetExtractor<Collection<Trip>> {
        @Override
        public Collection<Trip> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Long, Trip> tripMap = new HashMap<>();

            while (resultSet.next()) {
                if (!tripMap.keySet().contains(resultSet.getLong("id"))) {
                    Trip trip = new Trip();
                    trip.setId(resultSet.getLong("id"));
                    trip.setLandmark(landmarkRepository.findById(resultSet.getInt("landmark")));
                    trip.setTransportCompany(companyRepository.findById(resultSet.getInt("company")));
                    trip.setDate(resultSet.getObject("date", LocalDate.class));
                    trip.setStartTime(resultSet.getObject("start_time", LocalTime.class));
                    trip.setPrice(resultSet.getDouble("price"));
                    trip.setAvailablePlaces(resultSet.getInt("available_places"));

                    tripMap.put(trip.getId(), trip);
                }
            }
            return tripMap.values();
        }
    }
}
