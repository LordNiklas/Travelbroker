import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class HotelBookingSystem {
    public static void main(String[] args) {
        List<Hotel> hotels = Arrays.asList(
                new Hotel("Fujiyama", 5),
                new Hotel("Royal", 3),
                new Hotel("Boomerang", 4)
        );

        BookingService bookingService = new BookingService(hotels);
        TravelBroker travelBroker = new TravelBroker(bookingService);

        TourismCompany company1 = new TourismCompany("HolidayInTheMountains", travelBroker);
        TourismCompany company2 = new TourismCompany("TravelToTheSea", travelBroker);
        TourismCompany company3 = new TourismCompany("AwayFromHome", travelBroker);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wählen Sie ein Reisebüro:");
        System.out.println("1 - HolidayInTheMountains");
        System.out.println("2 - TravelToTheSea");
        System.out.println("3 - AwayFromHome");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Zeilenumbruch abfangen
        TourismCompany selectedCompany;
        switch (choice) {
            case 1:
                selectedCompany = company1;
                break;
            case 2:
                selectedCompany = company2;
                break;
            case 3:
                selectedCompany = company3;
                break;
            default:
                System.out.println("Ungültige Auswahl, Programm wird beendet.");
                return;
        }

        List<String> chosenHotels = new ArrayList<>();
        String lastChosenHotel = "";
        while (true) {
            System.out.println("Wählen Sie ein Hotel (oder 'q' zum Beenden):");
            for (int i = 0; i < hotels.size(); i++) {
                System.out.println((i + 1) + " - " + hotels.get(i).getName());
            }
            String hotelChoice = scanner.nextLine();
            if (hotelChoice.equalsIgnoreCase("q")) {
                break;
            }
            try {
                int hotelIndex = Integer.parseInt(hotelChoice.trim()) - 1;
                if (hotelIndex >= 0 && hotelIndex < hotels.size()) {
                    String selectedHotel = hotels.get(hotelIndex).getName();
                    // Überprüfen, ob dasselbe Hotel hintereinander gewählt wurde
                    if (!selectedHotel.equals(lastChosenHotel)) {
                        chosenHotels.add(selectedHotel);
                        lastChosenHotel = selectedHotel; // Setzen des zuletzt gewählten Hotels
                        System.out.println("Hotel '" + selectedHotel + "' hinzugefügt.");
                    } else {
                        System.out.println("Das gleiche Hotel kann nicht hintereinander gewählt werden.");
                    }
                } else {
                    System.out.println("Ungültige Auswahl. Bitte erneut eingeben.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte eine Nummer wählen.");
            }
        }

        System.out.println("Geben Sie die Aufenthaltsdauer in Wochen für jedes Hotel an:");
        List<Integer> stayDurations = new ArrayList<>();
        for (String hotel : chosenHotels) {
            System.out.print("Aufenthalt in " + hotel + " (Wochen): ");
            int duration = scanner.nextInt();
            stayDurations.add(duration);
        }

        int currentWeek = 1;
        for (int i = 0; i < chosenHotels.size(); i++) {
            String hotelName = chosenHotels.get(i);
            int duration = stayDurations.get(i);
            for (int j = 0; j < duration; j++) {
                selectedCompany.requestBooking(Arrays.asList(hotelName), currentWeek);
                currentWeek++;
            }
        }

        System.out.println("Buchung abgeschlossen.");
    }
}
