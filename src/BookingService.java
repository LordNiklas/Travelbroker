import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class BookingService {
    private List<Hotel> hotels;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public BookingService(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public String bookHotel(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                if (hotel.reserveRoom()) {
                    return log("Room reserved at " + hotel.getName());
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