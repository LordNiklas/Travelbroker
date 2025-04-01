import java.util.HashSet;
import java.util.Set;

public class Hotel {
    private String name;
    private int availableRooms;
    private Set<Integer> bookedWeeks;

    public Hotel(String name, int availableRooms) {
        this.name = name;
        this.availableRooms = availableRooms;
        this.bookedWeeks = new HashSet<>();
    }

    public synchronized boolean reserveRoom(int week) {
        if (availableRooms > 0 && !bookedWeeks.contains(week)) {
            availableRooms--;
            bookedWeeks.add(week);
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public boolean isWeekBooked(int week) {
        return bookedWeeks.contains(week);
    }
}