import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelBookingSystem {
    public static void main(String[] args) {
        // Create sample hotels
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Fujiyama", 5));  // 5 rooms available
        hotels.add(new Hotel("Royal", 3));     // 3 rooms available
        hotels.add(new Hotel("Boomerang", 4)); // 4 rooms available

        // Create BookingService with the list of hotels
        BookingService bookingService = new BookingService(hotels);

        // Example travel companies and trip duration
        TravelBroker selectedCompany = new TravelBroker(bookingService);

        // Initialize user input
        Scanner scanner = new Scanner(System.in);

        // Step 1: Select a travel agency
        System.out.println("Choose a travel agency:");
        System.out.println("1 - HolidayInTheMountains");
        System.out.println("2 - TravelToTheSea");
        System.out.println("3 - AwayFromHome");
        int agencyChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

        // Assign the corresponding company based on selection
        if (agencyChoice == 1) {
            System.out.println("Selected HolidayInTheMountains.");
        } else if (agencyChoice == 2) {
            System.out.println("Selected TravelToTheSea.");
        } else if (agencyChoice == 3) {
            System.out.println("Selected AwayFromHome.");
        } else {
            System.out.println("Invalid choice. Exiting...");
            return;
        }

        // Step 2: Enter trip start week
        System.out.print("Enter the start week of the trip (1 to 100): ");
        int startWeek = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

        // Step 3: Choose hotels
        List<String> chosenHotels = new ArrayList<>();
        List<Integer> stayDurations = new ArrayList<>();

        while (true) {
            System.out.println("Choose a hotel (or 'q' to quit):");
            for (int i = 0; i < hotels.size(); i++) {
                System.out.println((i + 1) + " - " + hotels.get(i).getName());
            }
            String hotelChoice = scanner.nextLine();

            if (hotelChoice.equals("q")) {
                break;
            }

            try {
                int choice = Integer.parseInt(hotelChoice);
                if (choice < 1 || choice > hotels.size()) {
                    System.out.println("Invalid input. Please choose a number.");
                    continue;
                }

                String hotelName = hotels.get(choice - 1).getName();
                chosenHotels.add(hotelName);
                System.out.print("Stay in " + hotelName + " (weeks): ");
                int stayDuration = scanner.nextInt();
                scanner.nextLine();  // Consume newline character
                stayDurations.add(stayDuration);
                System.out.println("Hotel '" + hotelName + "' added.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please choose a number.");
            }
        }

        // Step 4: Attempt booking for each hotel
        int currentWeek = startWeek;
        List<String> failedHotels = new ArrayList<>();  // List of failed hotels

        for (int i = 0; i < chosenHotels.size(); i++) {
            String hotelName = chosenHotels.get(i);
            int duration = stayDurations.get(i);

            // Attempt to book for the specified duration
            List<String> currentHotelBookings = new ArrayList<>();
            for (int j = 0; j < duration; j++) {
                boolean success = selectedCompany.requestBooking(hotelName, currentWeek);

                if (!success) {
                    System.out.println("Booking failed at " + hotelName + " for week " + currentWeek);
                    failedHotels.add(hotelName);  // Store failed hotel name in the list
                    selectedCompany.rollbackBookings(failedHotels, currentWeek);  // Call rollback with the list of failed bookings and current week
                    return;  // Cancel the entire booking process
                }

                currentHotelBookings.add(hotelName);
                currentWeek++;
            }
        }

        System.out.println("Booking completed.");
    }
}
