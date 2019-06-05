package travel.tool.epo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ipop
 */
@Getter
@AllArgsConstructor
public class BookingEpo {
    private Long id;
    private TripEpo trip;
    private String customer;
    private String phoneNumber;
    private Integer tickets;

    private BookingEpo(){}
}
