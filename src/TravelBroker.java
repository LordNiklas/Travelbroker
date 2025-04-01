// TravelBroker.java
import java.util.List;

public class TravelBroker {
    private BookingService bookingService;

    // Constructor to initialize the booking service
    public TravelBroker(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Method to process multiple booking requests asynchronously
    public void processBookingRequests(List<String> hotelRequests) {
        for (String hotelRequest : hotelRequests) {
            String result = bookingService.bookHotel(hotelRequest);
            System.out.println(result);
        }
    }
}
