public class TourismCompany {
    private String name;
    private TravelBroker travelBroker;

    public TourismCompany(String name, TravelBroker travelBroker) {
        this.name = name;
        this.travelBroker = travelBroker;
    }

    // Books a hotel for a given week
    public boolean requestBooking(String hotelName, int week) {
        // Call the TravelBroker to try the booking
        return travelBroker.requestBooking(hotelName, week);
    }


}
