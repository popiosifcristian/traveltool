package travel.tool.epo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author ipop
 */
@Getter
@AllArgsConstructor
public class TripEpo {
    private Long id;
    private LandmarkEpo landmark;
    private CompanyEpo transportCompany;
    private LocalDate date;
    private LocalTime startTime;
    private double price;
    private int availablePlaces;

    private TripEpo() {}
}
