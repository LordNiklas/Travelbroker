import java.util.*;

public class TravelBroker {
    private BookingService bookingService;

    public TravelBroker(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void processBookingRequests(List<String> hotelNames, int startWeek, List<Integer> durations) {
        int currentWeek = startWeek;
        List<String> successfulBookings = new ArrayList<>();

        for (int i = 0; i < hotelNames.size(); i++) {
            String hotelName = hotelNames.get(i);
            int duration = durations.get(i);
            for (int week = 0; week < duration; week++) {
                String bookingResult = bookingService.bookHotel(hotelName, currentWeek);
                if (bookingResult.contains("No rooms available")) {
                    System.out.println("Booking failed at hotel: " + hotelName + " in week: " + currentWeek);
                    rollbackBookings(successfulBookings);
                    return;
                }
                successfulBookings.add(hotelName + " (Week: " + currentWeek + ")");
                currentWeek++;
            }
        }

        System.out.println("Everything booked successfully:");
        successfulBookings.forEach(System.out::println);
    }

    private void rollbackBookings(List<String> successfulBookings) {
        System.out.println("Rolling back successful bookings:");
        successfulBookings.forEach(booking -> System.out.println("- Cancelled: " + booking));
    }
}
