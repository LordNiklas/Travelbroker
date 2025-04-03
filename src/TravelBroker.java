import java.util.List;

public class TravelBroker {
    private List<Hotel> hotels;

    public TravelBroker(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    // Attempts to book a hotel for a specific week
    public boolean requestBooking(String hotelName, int week) {
        Hotel hotel = findHotelByName(hotelName);

        if (hotel == null) {
            LogUtils.log("Hotel not found: " + hotelName);
            return false;
        }

        String bookingMessage = hotel.bookRoomForWeek(week);
        LogUtils.log(bookingMessage);

        return bookingMessage.startsWith("Room booked");
    }

    // Finds a hotel by its name
    private Hotel findHotelByName(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(hotelName)) {
                return hotel;
            }
        }
        return null;
    }

    // Rolls back the bookings for a list of hotels that failed
    public void rollbackBookings(List<String> failedHotels, int week) {
        LogUtils.log("Rolling back bookings for week " + week);

        for (String hotelName : failedHotels) {
            Hotel hotel = findHotelByName(hotelName);
            if (hotel != null) {
                hotel.cancelBookingForWeek(week);  // Cancel the booking for the specific week
            }
        }
    }
}
