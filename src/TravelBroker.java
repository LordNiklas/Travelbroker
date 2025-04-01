import java.util.ArrayList;
import java.util.List;

class TravelBroker {
    private BookingService bookingService;
    private List<String> successfulBookings = new ArrayList<>();

    public TravelBroker(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void processBookingRequests(List<String> requests) {
        for (String hotelName : requests) {
            String result = bookingService.bookHotel(hotelName);
            if (result.contains("Room reserved")) {
                successfulBookings.add(hotelName);
            } else {
                cancelSuccessfulBookings();
                break;
            }
        }
        if (successfulBookings.size() == requests.size()) {
            System.out.println("Everything booked successfully.");
        }
    }

    private void cancelSuccessfulBookings() {
        System.out.println("Booking failed. Rolling back successful reservations:");
        for (String hotelName : successfulBookings) {
            System.out.println("Cancelled reservation at: " + hotelName);
        }
        successfulBookings.clear();
    }
}
