import javax.swing.*;
import java.awt.*;

class BookingGUI {
    private JTextArea logArea;

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Hotel Booking Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logArea = new JTextArea(20, 50);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void log(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }
}