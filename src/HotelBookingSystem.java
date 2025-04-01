import java.util.*;
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
        int startWeek = 0;

        while (true) {
            System.out.println("Enter hotels to book (comma separated, max 5 hotels):");
            String input = scanner.nextLine();
            List<String> enteredHotels = Arrays.asList(input.split(","));
            if (enteredHotels.size() > 5) {
                System.out.println("Too many hotels. Please select up to 5.");
                continue;
            }
            boolean allValid = true;
            String previousHotel = "";
            for (String hotelName : enteredHotels) {
                final String currentHotel = hotelName.trim();
                boolean found = hotels.stream().anyMatch(h -> h.getName().equalsIgnoreCase(currentHotel));
                if (!found) {
                    allValid = false;
                    System.out.println("Invalid hotel: " + hotelName);
                    System.out.println("Available hotels are:");
                    hotels.forEach(h -> System.out.println("- " + h.getName()));
                    break;
                }
                if (hotelName.equalsIgnoreCase(previousHotel)) {
                    allValid = false;
                    System.out.println("Error: Consecutive bookings cannot be in the same hotel: " + hotelName);
                    break;
                }
                previousHotel = hotelName;
            }
            if (allValid) {
                System.out.println("Enter the start week number (0-99) for booking:");
                startWeek = scanner.nextInt();
                scanner.nextLine();
                if (startWeek < 0 || startWeek >= 100) {
                    System.out.println("Invalid week number. Please choose a number between 0 and 99.");
                    continue;
                }
                System.out.println("Enter the duration (in weeks) for each hotel, separated by commas:");
                String durations = scanner.nextLine();
                List<Integer> weeks = new ArrayList<>();
                try {
                    Arrays.stream(durations.split(",")).forEach(d -> weeks.add(Integer.parseInt(d.trim())));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid duration format. Please enter numbers separated by commas.");
                    continue;
                }
                if (weeks.size() != enteredHotels.size()) {
                    System.out.println("Number of durations does not match the number of hotels.");
                    continue;
                }
                travelBroker.processBookingRequests(requests, startWeek, weeks);
                break;
            } else {
                System.out.println("Please try again with valid hotel names.");
            }
        }
    }
}
