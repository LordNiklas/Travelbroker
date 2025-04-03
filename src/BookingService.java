import java.util.*;

class BookingService {
    private List<Hotel> hotels;
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
                    return LogUtils.log("Hotel bereits für Woche " + week + " gebucht: " + hotelName);
                }
                if (hotel.reserveRoom(week)) {
                    bookings.add(week);
                    return LogUtils.log("Zimmer reserviert im Hotel " + hotel.getName() + " für Woche " + week);
                } else {
                    return LogUtils.log("Keine Zimmer verfügbar im Hotel " + hotel.getName());
                }
            }
        }
        return LogUtils.log("Hotel nicht gefunden: " + hotelName);
    }

    public void cancelBooking(String hotelName, int week) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                hotel.releaseRoom(week);
                LogUtils.log("Buchung storniert im Hotel " + hotel.getName() + " für Woche " + week);
                return;
            }
        }
    }

    public List<String> getAvailableHotels() {
        List<String> availableHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            availableHotels.add(hotel.getName() + " (" + (hotel.isFullyBooked() ? "Belegt" : "Frei") + ")");
        }
        return availableHotels;
    }
}