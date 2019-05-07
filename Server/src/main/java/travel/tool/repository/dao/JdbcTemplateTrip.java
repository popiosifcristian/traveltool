package travel.tool.repository.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import travel.tool.model.Trip;
import travel.tool.repository.ICompanyRepository;
import travel.tool.repository.ILandmarkRepository;
import travel.tool.repository.ITripRepository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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

    @Override
    public Trip update(Trip trip) {
        Long newId;
        if (trip.getId() > 0) {
            newId = jdbcTemplate.queryForObject(TRIP_UPDATE, new Object[]{
                    trip.getLandmark().getId(),
                    trip.getTransportCompany().getId(),
                    Date.valueOf(trip.getDate()),
                    Time.valueOf(trip.getStartTime()),
                    trip.getPrice(),
                    trip.getAvailablePlaces(),
                    trip.getId()
            }, (resultSet, i) -> resultSet.getLong(1));
        } else {
            newId = jdbcTemplate.queryForObject(TRIP_SAVE, new Object[]{
                    trip.getLandmark().getId(),
                    trip.getTransportCompany().getId(),
                    Date.valueOf(trip.getDate()),
                    Time.valueOf(trip.getStartTime()),
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

    @Override
    public List<Trip> searchByNameDateAndTime(String name, LocalDate date, LocalTime startTime) {
        return new ArrayList<>(
                jdbcTemplate.query(TRIP_GET_ALL_BY_NAME_DATE_TIME, new TripResultSetExtractor(), name, Date.valueOf(date), Time.valueOf(startTime)));
    }

    @Override
    public int updateAvailablePlaces(long id, int availablePlaces) {
        return jdbcTemplate.update(TRIP_UPDATE_AVAILABLE_PLACES, availablePlaces, id);
    }

    @Override
    public int getAvailablePlaces(long id) {
        return jdbcTemplate.queryForObject(TRIP_GET_AVAILABLE_PLACES, new Object[]{id}, (resultSet, i) -> resultSet.getInt(1));
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
                    Date date = (Date) resultSet.getObject("date");
                    trip.setDate(date.toLocalDate());
                    Time time = (Time) resultSet.getObject("start_time");
                    trip.setStartTime(time.toLocalTime());
                    trip.setPrice(resultSet.getDouble("price"));
                    trip.setAvailablePlaces(resultSet.getInt("available_places"));

                    tripMap.put(trip.getId(), trip);
                }
            }
            return tripMap.values();
        }
    }
}
