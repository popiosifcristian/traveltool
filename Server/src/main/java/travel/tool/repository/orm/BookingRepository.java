package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import travel.tool.model.Booking;
import travel.tool.repository.IBookingRepository;

/**
 * @author ipop
 */
//@Repository("bookingRepository")
public interface BookingRepository extends JpaRepository<Booking, Long>, IBookingRepository {

}
