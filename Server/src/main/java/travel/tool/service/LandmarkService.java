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
    @Qualifier("jdbcTemplateLandmark")
    private ILandmarkRepository landmarkRepository;

    public List<Landmark> getAll() {
        return new ArrayList<>(landmarkRepository.getAll());
    }

    public Landmark findById(long id) {
        return landmarkRepository.findById(id);
    }

    public Landmark update(Landmark landmark) {
        return landmarkRepository.update(landmark);
    }

    public boolean delete(Landmark landmark) {
        return landmarkRepository.delete(landmark);
    }
}
