import java.util.HashSet;
import java.util.Set;

public class Hotel {
    private String name;
    private int totalRooms;
    private Set<Integer> bookedWeeks; // Tracks the weeks for which rooms have been booked

    // Constructor to initialize hotel name, total rooms, and booked weeks
    public Hotel(String name, int totalRooms) {
        this.name = name;
        this.totalRooms = totalRooms;
        this.bookedWeeks = new HashSet<>();
    }

    // Returns the name of the hotel
    public String getName() {
        return name;
    }

    // Checks if there is an available room for a specific week
    public boolean isRoomAvailable(int week) {
        // A room is available if less than the total rooms are booked for the given week
        return bookedWeeks.size() < totalRooms;
    }

    // Books a room for a specific week if available
    public String bookRoomForWeek(int week) {
        if (isRoomAvailable(week)) {
            bookedWeeks.add(week);  // Book the room for the week
            return LogUtils.log("Room booked at " + name + " for week " + week);
        }
        return LogUtils.log("Booking failed: Hotel is fully booked for week " + week);
    }

    // Cancels a booking for a specific week
    public void cancelBookingForWeek(int week) {
        bookedWeeks.remove(week);  // Remove the booking for the specified week
        LogUtils.log("Booking at " + name + " for week " + week + " has been canceled.");
    }
}
