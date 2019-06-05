package travel.tool.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.tool.epo.TripEpo;
import travel.tool.model.Trip;

/**
 * @author ipop
 */
@Service
public class TripMapper extends GenericMapper<Trip, TripEpo> {
    @Autowired
    private LandmarkMapper landmarkMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Trip toInternal(TripEpo epo) {
        return new Trip(epo.getId(), landmarkMapper.toInternal(epo.getLandmark()), companyMapper.toInternal(epo.getTransportCompany()), epo.getDate(), epo.getStartTime(), epo.getPrice(), epo.getAvailablePlaces());
    }

    @Override
    public TripEpo toExternal(Trip model) {
        return new TripEpo(model.getId(), landmarkMapper.toExternal(model.getLandmark()), companyMapper.toExternal(model.getTransportCompany()), model.getDate(), model.getStartTime(), model.getPrice(), model.getAvailablePlaces());
    }
}
