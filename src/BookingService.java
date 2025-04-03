import java.util.List;

public class BookingService {
    private List<Hotel> hotels;

    public BookingService(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    // Bucht ein Zimmer in einem Hotel für eine bestimmte Woche
    public String bookHotel(String hotelName, int week) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(hotelName)) {
                return hotel.bookRoomForWeek(week);  // Ruft die Methode bookRoomForWeek auf
            }
        }
        return "Hotel not found";
    }

    // Storniert eine Buchung für ein bestimmtes Hotel und eine bestimmte Woche
    public void cancelBooking(String hotelName, int week) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(hotelName)) {
                hotel.cancelBookingForWeek(week);  // Storniert die Buchung für die angegebene Woche
            }
        }
    }

    // Storniert alle Buchungen für eine Liste von Hotels und eine bestimmte Woche
    public void cancelAllBookings(List<String> hotelsToCancel, int week) {
        for (String hotelName : hotelsToCancel) {
            cancelBooking(hotelName, week);
        }
    }
}
