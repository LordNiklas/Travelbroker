import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

class BookingGUI {
    private JTextArea logArea;
    private JTextField bookingField;
    private JButton bookButton;
    private TravelBroker travelBroker;

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Hotel Booking Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logArea = new JTextArea(20, 50);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        bookingField = new JTextField(30);
        bookButton = new JButton("Book Hotels");
        bookButton.addActionListener(e -> initiateBooking());

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter hotels (comma separated):"));
        panel.add(bookingField);
        panel.add(bookButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private void initiateBooking() {
        String input = bookingField.getText();
        if (input.isEmpty()) return;
        List<String> requests = Arrays.asList(input.split(","));
        log("Starting booking for: " + requests);
        travelBroker.processBookingRequests(requests);
    }

    public void log(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }

    public void setTravelBroker(TravelBroker travelBroker) {
        this.travelBroker = travelBroker;
    }
}