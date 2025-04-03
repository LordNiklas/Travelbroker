import java.util.List;

public class TravelBroker {
    private BookingService bookingService;

    public TravelBroker(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Booking request for a single hotel
    public boolean requestBooking(String hotelName, int week) {
        String result = bookingService.bookHotel(hotelName, week);
        return result.contains("Room booked successfully");
    }

    // Rollback for a specific hotel
    public void rollbackBookingsForHotel(List<String> currentHotelBookings, int week) {
        for (String hotelName : currentHotelBookings) {
            System.out.println("Rolling back booking at " + hotelName + " for week " + week);
            bookingService.cancelBooking(hotelName, week);
        }
    }

    // Rollback for all bookings after a failure
    public void rollbackBookings(List<String> bookings, int week) {
        for (String booking : bookings) {
            System.out.println("Rolling back booking: " + booking);
        }
        System.out.println("Bookings rolled back until week: " + week);
    }
}
