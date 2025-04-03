import java.util.ArrayList;
import java.util.List;

class TravelBroker {
    private BookingService bookingService;

    public TravelBroker(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void processBookingRequests(List<String> hotelNames, int week) {
        List<String> successfulBookings = new ArrayList<>();
        for (String hotelName : hotelNames) {
            String result = bookingService.bookHotel(hotelName, week);
            if (result.contains("Keine Zimmer verfügbar")) {
                rollbackBookings(successfulBookings, week);
                LogUtils.log("Buchung bei " + hotelName + " fehlgeschlagen, vorherige Buchungen werden zurückgesetzt.");
                return;
            }
            successfulBookings.add(hotelName);
        }
        LogUtils.log("Alle Buchungen erfolgreich abgeschlossen.");
    }

    private void rollbackBookings(List<String> successfulBookings, int week) {
        for (String hotelName : successfulBookings) {
            bookingService.cancelBooking(hotelName, week);
            LogUtils.log("Rückbuchung durchgeführt für " + hotelName + " in Woche " + week);
        }
    }
}