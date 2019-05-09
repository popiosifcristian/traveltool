package travel.tool.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ipop
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 6643583622925481529L;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip")
    private Trip trip;
    @Column(name = "customer")
    private String customer;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "tickets")
    private int tickets;

    public Booking(long id, Trip trip, String customer, String phoneNumber, int tickets) {
        super(id);
        this.trip = trip;
        this.customer = customer;
        this.phoneNumber = phoneNumber;
        this.tickets = tickets;
    }

    public Booking(Trip trip, String customer, String phoneNumber, int tickets) {
        super(0);
        this.trip = trip;
        this.customer = customer;
        this.phoneNumber = phoneNumber;
        this.tickets = tickets;
    }
}
