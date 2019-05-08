package travel.tool.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ipop
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Booking extends AbstractEntity implements Serializable {
    private Trip trip;
    private String customer;
    private String phoneNumber;
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
