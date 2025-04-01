// Hotel.java
public class Hotel {
    private String name;
    private int availableRooms;

    // Constructor to initialize hotel name and available rooms
    public Hotel(String name, int availableRooms) {
        this.name = name;
        this.availableRooms = availableRooms;
    }

    // Method to reserve a room; synchronizes access to ensure thread safety
    public synchronized boolean reserveRoom() {
        if (availableRooms > 0) {
            availableRooms--;
            return true;
        }
        return false;
    }

    // Getter for hotel name
    public String getName() {
        return name;
    }

    // Getter for the number of available rooms
    public int getAvailableRooms() {
        return availableRooms;
    }
}
