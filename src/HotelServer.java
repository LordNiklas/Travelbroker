import java.util.*;
import java.text.SimpleDateFormat;

public class HotelServer {
    public static void main(String[] args) {
        // Initialize hotels with names and room counts
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("HolidayInTheMountains", 10));
        hotels.add(new Hotel("TravelToTheSea", 5));
        hotels.add(new Hotel("AwayFromHome", 8));

        // Initialize the booking service and travel broker
        BookingService bookingService = new BookingService(hotels);
        TravelBroker travelBroker = new TravelBroker(bookingService);

        // Example booking requests from clients
        List<String> requests = Arrays.asList("HolidayInTheMountains", "TravelToTheSea", "AwayFromHome");

        // Process the booking requests using the travel broker
        System.out.println("Starting booking simulation...");
        travelBroker.processBookingRequests(requests);
    }
}