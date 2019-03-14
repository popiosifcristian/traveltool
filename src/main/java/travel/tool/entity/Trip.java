package travel.tool.entity;

import lombok.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.List;

/**
 * @author ipop
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    private Long id;
    private Landmark landmark;
    private Company transportCompany;
    private LocalDate date;
    private LocalTime startTime;
    private double price;
    private int availablePlaces;
    private List<Customer> customerList;
}
