package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import travel.tool.model.Landmark;
import travel.tool.repository.ILandmarkRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ipop
 */
@Service
public class LandmarkService {
    @Autowired
    @Qualifier("landmarkRepository")
    private ILandmarkRepository landmarkRepository;

    public List<Landmark> findAll() {
        return new ArrayList<>(landmarkRepository.findAll());
    }

    public Landmark getOne(long id) {
        return landmarkRepository.getOne(id);
    }

    public Landmark save(Landmark landmark) {
        return landmarkRepository.save(landmark);
    }

    public void delete(Landmark landmark) {
        landmarkRepository.delete(landmark);
    }
}
