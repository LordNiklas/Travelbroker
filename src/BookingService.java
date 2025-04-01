import java.text.SimpleDateFormat;
import java.util.*;

public class BookingService {
    private List<Hotel> hotels;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Map<String, Set<Integer>> hotelBookings = new HashMap<>();

    public BookingService(List<Hotel> hotels) {
        this.hotels = hotels;
        for (Hotel hotel : hotels) {
            hotelBookings.put(hotel.getName(), new HashSet<>());
        }
    }

    public String bookHotel(String hotelName, int week) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                Set<Integer> bookings = hotelBookings.get(hotelName);
                if (bookings.contains(week)) {
                    return log("Hotel already booked for week " + week + " at " + hotelName);
                }
                if (hotel.reserveRoom(week)) {
                    bookings.add(week);
                    return log("Room reserved at " + hotel.getName() + " for week " + week);
                } else {
                    return log("No rooms available at " + hotel.getName());
                }
            }
        }
        return log("Hotel not found: " + hotelName);
    }

    private String log(String message) {
        String timestamp = dateFormat.format(new Date());
        String logMessage = timestamp + " - " + message;
        System.out.println(logMessage);
        return logMessage;
    }
}
