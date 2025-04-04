import org.zeromq.ZMQ;

import java.util.List;
import java.util.Scanner;

public class HotelBookingSystem {
    public static void main(String[] args) {
        // Beispiel-Hotels
        List<String> hotelAddresses = List.of("localhost:5001", "localhost:5002", "localhost:5003");

        // TravelBroker mit Hoteladressen initialisieren
        TravelBroker travelBroker = new TravelBroker(hotelAddresses);

        // TravelBroker starten
        new Thread(travelBroker::start).start();

        // Reisebüros erstellen und starten
        TravelAgency travelAgency = new TravelAgency();
        travelAgency.start();

        // Benutzerinteraktion für Buchungen
        Scanner scanner = new Scanner(System.in);

        // Wähle ein Hotel und Woche
        System.out.print("Wähle ein Hotel (Fujiyama, Royal, Boomerang): ");
        String hotelName = scanner.nextLine();

        System.out.print("Gib die Woche für die Buchung ein: ");
        int week = scanner.nextInt();

        // Buchung an TravelBroker senden
        travelAgency.makeBooking(hotelName, week);
    }
}
