package travel.tool.repository;

import travel.tool.model.Booking;

/**
 * @author ipop
 */
public interface IBookingRepository extends ICrudRepository<Booking> {
    int getTicketsById(long id);
}
