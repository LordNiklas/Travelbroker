import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hotel {
    private String name;
    private int totalRooms;
    private int availableRooms;
    private Set<Integer> bookedWeeks;
    private Lock lock;

    public Hotel(String name, int totalRooms) {
        this.name = name;
        this.totalRooms = totalRooms;
        this.availableRooms = totalRooms;
        this.bookedWeeks = new HashSet<>();
        this.lock = new ReentrantLock();
    }

    public boolean reserveRoom(int week) {
        lock.lock();
        try {
            if (availableRooms > 0 && !bookedWeeks.contains(week)) {
                availableRooms--;
                bookedWeeks.add(week);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void releaseRoom(int week) {
        lock.lock();
        try {
            if (bookedWeeks.contains(week)) {
                availableRooms++;
                bookedWeeks.remove(week);
            }
        } finally {
            lock.unlock();
        }
    }

    public String getName() {
        return name;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public boolean isWeekBooked(int week) {
        lock.lock();
        try {
            return bookedWeeks.contains(week);
        } finally {
            lock.unlock();
        }
    }

    public boolean isFullyBooked() {
        lock.lock();
        try {
            return availableRooms == 0;
        } finally {
            lock.unlock();
        }
    }
}
