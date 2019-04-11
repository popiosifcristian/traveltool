package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.tool.entity.Trip;
import travel.tool.repository.ITripRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ipop
 */
@Service
public class TripService {
    @Autowired
    private ITripRepository tripRepository;

    public List<Trip> getAll() {
        return new ArrayList<>(tripRepository.getAll());
    }

    public Trip findById(long id) {
        return tripRepository.findById(id);
    }

    public Trip update(Trip trip) {
        return tripRepository.update(trip);
    }

    public boolean delete(Trip trip) {
        return tripRepository.delete(trip);
    }

    public List<Trip> searchByNameDateAndTime(String name, LocalDate date, LocalTime startTime) {
        return tripRepository.searchByNameDateAndTime(name, date, startTime);
    }

    public void updateAvailablePlaces(long id, int availablePlaces) {
        tripRepository.updateAvailablePlaces(id, availablePlaces);
    }

    public int getAvailablePlaces(long id) {
        return tripRepository.getAvailablePlaces(id);
    }
}
