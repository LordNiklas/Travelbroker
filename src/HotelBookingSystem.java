import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HotelBookingSystem {
    public static void main(String[] args) {
        // Initialize GUI for live visualization
        BookingGUI gui = new BookingGUI();
        SwingUtilities.invokeLater(gui::createAndShowGUI);

        // Initialize hotels with names and room counts
        List<String> hotelNames = Arrays.asList("HolidayInTheMountains", "TravelToTheSea", "AwayFromHome");
        List<Integer> roomCounts = Arrays.asList(10, 5, 8);

        // Start hotel servers as separate threads
        for (int i = 0; i < hotelNames.size(); i++) {
            String name = hotelNames.get(i);
            int rooms = roomCounts.get(i);
            new Thread(() -> new HotelServer(name, rooms, gui).start()).start();
        }

        // Allow servers to start
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Initialize the travel broker
        TravelBroker travelBroker = new TravelBroker(gui);

        // Example multi-hotel booking request
        List<String> requests = Arrays.asList("HolidayInTheMountains", "TravelToTheSea", "AwayFromHome");
        gui.log("Starting multi-hotel booking simulation...");
        travelBroker.processBookingRequests(requests);
    }
}
