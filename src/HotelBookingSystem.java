import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.net.*;

public class HotelBookingSystem {
    public static void main(String[] args) {
        // Initialize hotels with names and room counts
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("HolidayInTheMountains", 10));
        hotels.add(new Hotel("TravelToTheSea", 5));
        hotels.add(new Hotel("AwayFromHome", 8));

        // Initialize booking service
        BookingService bookingService = new BookingService(hotels);
        TravelBroker travelBroker = new TravelBroker(bookingService);

        Scanner scanner = new Scanner(System.in);
        List<String> requests = new ArrayList<>();
        while (true) {
            System.out.println("Enter hotels to book (comma separated):");
            String input = scanner.nextLine();
            List<String> enteredHotels = Arrays.asList(input.split(","));
            boolean allValid = true;
            for (String hotelName : enteredHotels) {
                boolean found = hotels.stream().anyMatch(h -> h.getName().equalsIgnoreCase(hotelName.trim()));
                if (!found) {
                    allValid = false;
                    System.out.println("Invalid hotel: " + hotelName.trim());
                    System.out.println("Available hotels are:");
                    hotels.forEach(h -> System.out.println("- " + h.getName()));
                    break;
                }
            }
            if (allValid) {
                requests.addAll(enteredHotels);
                break;
            } else {
                System.out.println("Please try again with valid hotel names.");
            }
        }
        travelBroker.processBookingRequests(requests);
    }
}