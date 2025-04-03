import java.util.List;

class BookingService {
    private List<Hotel> hotels;

    public BookingService(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    // Books a hotel for a specific week
    public String bookHotel(String hotelName, int week) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(hotelName)) {
                return hotel.bookRoomForWeek(week);
            }
        }
        return "Hotel not found";
    }

    // Cancels a booking for a specific hotel and week
    public void cancelBooking(String hotelName, int week) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(hotelName)) {
                hotel.cancelBookingForWeek(week);
            }
        }
    }

    // Cancels all bookings for a list of hotels and a specific week
    public void cancelAllBookings(List<String> hotelsToCancel, int week) {
        for (String hotelName : hotelsToCancel) {
            cancelBooking(hotelName, week);
        }
    }
}
