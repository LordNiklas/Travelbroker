// BookingService.java
import java.util.List;

public class BookingService {
    private List<Hotel> hotels;

    // Constructor to initialize the list of hotels
    public BookingService(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    // Method to book a room in a specific hotel by its name
    public String bookHotel(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                if (hotel.reserveRoom()) {
                    return "Room reserved at " + hotel.getName();
                } else {
                    return "No rooms available at " + hotel.getName();
                }
            }
        }
        return "Hotel not found: " + hotelName;
    }
}
