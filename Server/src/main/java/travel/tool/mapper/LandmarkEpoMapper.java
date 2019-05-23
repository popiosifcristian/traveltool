package travel.tool.mapper;

import org.springframework.stereotype.Service;
import travel.tool.epo.LandmarkEpo;
import travel.tool.model.Landmark;

/**
 * @author ipop
 */
@Service
public class LandmarkEpoMapper extends GenericMapper<Landmark, LandmarkEpo>{
    @Override
    public Landmark toInternal(LandmarkEpo epo) {
        return new Landmark(epo.getId(),epo.getName(),epo.getLocation(),epo.getDescription());
    }

    @Override
    public LandmarkEpo toExternal(Landmark model) {
        return new LandmarkEpo(model.getId(),model.getName(),model.getLocation(),model.getDescription());
    }
}
