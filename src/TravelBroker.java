import java.util.List;

class TravelBroker {
    private BookingService bookingService;

    public TravelBroker(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void processBookingRequests(List<String> requests) {
        System.out.println("Processing booking requests...");
        boolean allBooked = true;
        StringBuilder resultLog = new StringBuilder();

        for (String request : requests) {
            String result = bookingService.bookHotel(request.trim());
            resultLog.append(result).append("\n");

            if (result.contains("No rooms available") || result.contains("Hotel not found")) {
                allBooked = false;
                break;
            }
        }

        if (allBooked) {
            System.out.println("Everything booked successfully.");
        } else {
            System.out.println("Booking failed. Rolling back previous bookings.");
        }

        System.out.println(resultLog.toString());
    }
}