package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import travel.tool.model.Booking;
import travel.tool.repository.IBookingRepository;

/**
 * @author ipop
 */
@Repository("bookingRepository")
public interface BookingRepository extends JpaRepository<Booking, Long>, IBookingRepository {
    @Override
    @Query(value = "SELECT b.tickets FROM booking b WHERE b.id= :id", nativeQuery = true)
    int getTicketsById(@Param("id") long id);
}
