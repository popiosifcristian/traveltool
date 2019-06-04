package travel.tool.epo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime startTime;
    private double price;
    private int availablePlaces;

    private TripEpo() {}
}
