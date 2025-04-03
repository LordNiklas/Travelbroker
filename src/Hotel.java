class Hotel {
    private String name;
    private int availableRooms;

    public Hotel(String name, int availableRooms) {
        this.name = name;
        this.availableRooms = availableRooms;
    }

    public String getName() {
        return name;
    }

    public String bookRoomForWeek(int week) {
        if (availableRooms > 0) {
            availableRooms--;
            System.out.println("Room booked at " + name + " for week " + week);
            return "Room booked successfully";
        } else {
            return "No rooms available for week " + week;
        }
    }

    public void cancelBookingForWeek(int week) {
        availableRooms++;
        System.out.println("Booking at " + name + " for week " + week + " has been canceled.");
    }
}
