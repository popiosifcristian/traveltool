package travel.tool.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author ipop
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Trip extends AbstractEntity implements Serializable {
    private Landmark landmark;
    private Company transportCompany;
    private LocalDate date;
    private LocalTime startTime;
    private double price;
    private int availablePlaces;
    private List<Customer> customerList;

    public Trip(Landmark landmark, Company transportCompany, LocalDate date, LocalTime startTime, double price,
                int availablePlaces) {
        this.id = 0;
        this.landmark = landmark;
        this.transportCompany = transportCompany;
        this.date = date;
        this.startTime = startTime;
        this.price = price;
        this.availablePlaces = availablePlaces;
    }

    public Trip(long id, Landmark landmark, Company transportCompany, LocalDate date, LocalTime startTime, double price,
                int availablePlaces) {
        super(id);
        this.landmark = landmark;
        this.transportCompany = transportCompany;
        this.date = date;
        this.startTime = startTime;
        this.price = price;
        this.availablePlaces = availablePlaces;
        this.customerList = customerList;
    }
}
