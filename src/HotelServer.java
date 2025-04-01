// HotelServer.java
import java.util.ArrayList;
import java.util.List;

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
        List<String> requests = new ArrayList<>();
        requests.add("HolidayInTheMountains");
        requests.add("TravelToTheSea");
        requests.add("AwayFromHome");

        // Process the booking requests using the travel broker
        System.out.println("Starting booking simulation...");
        travelBroker.processBookingRequests(requests);

        // Simulate technical errors or business issues (optional feature)
        simulateErrors(hotels);
    }

    // Method to simulate technical errors in the system
    private static void simulateErrors(List<Hotel> hotels) {
        System.out.println("\nSimulating technical errors...");
        for (Hotel hotel : hotels) {
            System.out.println("Technical error occurred at hotel: " + hotel.getName());
            // Additional error handling logic can be implemented here
        }
    }
}
