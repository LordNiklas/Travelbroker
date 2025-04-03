import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelBookingSystem {
    public static void main(String[] args) {
        // Create example hotels
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Fujiyama", 15));
        hotels.add(new Hotel("Royal", 13));
        hotels.add(new Hotel("Boomerang", 14));

        // Create TravelBroker with the list of hotels
        TravelBroker selectedCompany = new TravelBroker(hotels);

        // Initialize user input scanner
        Scanner scanner = new Scanner(System.in);

        // Step 1: Choose a travel agency
        System.out.println("Choose a travel agency:");
        System.out.println("1 - HolidayInTheMountains");
        System.out.println("2 - TravelToTheSea");
        System.out.println("3 - AwayFromHome");
        int agencyChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

        // Select the agency based on input
        String agencyName = "";
        if (agencyChoice == 1) {
            agencyName = "HolidayInTheMountains";
        } else if (agencyChoice == 2) {
            agencyName = "TravelToTheSea";
        } else if (agencyChoice == 3) {
            agencyName = "AwayFromHome";
        } else {
            LogUtils.log("Invalid choice. Exiting...");
            return;
        }

        // Print agency selection timestamp
        LogUtils.log("Selected " + agencyName);

        // Step 2: Enter start week for the trip
        System.out.print("Enter the start week of the trip (1 to 100): ");
        int startWeek = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

        // Step 3: Choose hotels
        List<String> chosenHotels = new ArrayList<>();
        List<Integer> stayDurations = new ArrayList<>();
        String lastBookedHotel = "";  // Track last booked hotel

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
                    LogUtils.log("Invalid input. Please choose a number.");
                    continue;
                }

                String hotelName = hotels.get(choice - 1).getName();

                // Check if the same hotel as the last booked hotel is selected
                if (hotelName.equals(lastBookedHotel)) {
                    LogUtils.log("You cannot book the same hotel consecutively. Please choose a different one.");
                    continue;
                }

                chosenHotels.add(hotelName);
                System.out.print("Stay in " + hotelName + " (weeks): ");
                int stayDuration = scanner.nextInt();
                scanner.nextLine();  // Consume newline character
                stayDurations.add(stayDuration);

                // Set the last booked hotel
                lastBookedHotel = hotelName;

                LogUtils.log("Hotel '" + hotelName + "' added.");
            } catch (NumberFormatException e) {
                LogUtils.log("Invalid input. Please choose a number.");
            }
        }

        // Step 4: Try booking each hotel
        int currentWeek = startWeek;
        List<String> failedHotels = new ArrayList<>();  // List of failed hotels

        for (int i = 0; i < chosenHotels.size(); i++) {
            String hotelName = chosenHotels.get(i);
            int duration = stayDurations.get(i);

            // Attempt to book the hotel for the specified duration
            List<String> currentHotelBookings = new ArrayList<>();
            for (int j = 0; j < duration; j++) {
                boolean success = selectedCompany.requestBooking(hotelName, currentWeek);

                if (!success) {
                    LogUtils.log("Booking failed at " + hotelName + " for week " + currentWeek);
                    failedHotels.add(hotelName);  // Add failed hotel to the list
                    selectedCompany.rollbackBookings(failedHotels, currentWeek);  // Rollback failed bookings
                    return;  // Abort the entire booking process
                }

                currentHotelBookings.add(hotelName);
                currentWeek++;
            }
        }

        LogUtils.log("Booking completed.");
    }
}
