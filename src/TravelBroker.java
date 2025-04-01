import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class TravelBroker {
    private BookingGUI gui;

    public TravelBroker(BookingGUI gui) {
        this.gui = gui;
    }

    public void processBookingRequests(List<String> requests) {
        List<String> successfulBookings = new ArrayList<>();
        for (String hotelName : requests) {
            String result = bookHotel(hotelName);
            if (!"SUCCESS".equals(result)) {
                gui.log("Booking failed at hotel: " + hotelName + ". Rolling back...");
                rollbackBookings(successfulBookings);
                return;
            }
            gui.log("Booking successful at hotel: " + hotelName);
            successfulBookings.add(hotelName);
        }
        gui.log("All bookings completed successfully.");
    }

    private String bookHotel(String hotelName) {
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println("BOOK");
            return in.readLine();
        } catch (IOException e) {
            gui.log("Technical error while booking at " + hotelName);
            return "FAILURE";
        }
    }

    private void rollbackBookings(List<String> successfulBookings) {
        for (String hotel : successfulBookings) {
            gui.log("Cancelling booking at: " + hotel);
        }
    }
}

