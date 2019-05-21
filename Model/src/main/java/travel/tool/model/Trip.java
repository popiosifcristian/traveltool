package travel.tool.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
@Entity
@Table(name = "trip")
public class Trip extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 254069760428748522L;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "landmark")
    private Landmark landmark;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company")
    private Company transportCompany;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "price")
    private double price;
    @Column(name = "available_places")
    private int availablePlaces;
    @Transient
    private List<Customer> customerList;

    public Trip(Landmark landmark, Company transportCompany, LocalDate date, LocalTime startTime, double price,
                int availablePlaces) {
        super(0L);
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
