import java.util.*;

public class TourismCompany {
    private String name;
    private TravelBroker broker;

    public TourismCompany(String name, TravelBroker broker) {
        this.name = name;
        this.broker = broker;
    }

    public void requestBooking(List<String> hotelNames, int week) {
        System.out.println(name + " requesting booking for hotels " + hotelNames + " in week " + week);
        broker.processBookingRequests(hotelNames, week);
    }
}