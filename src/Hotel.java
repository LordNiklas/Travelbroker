class Hotel {
    private String name;
    private int availableRooms;

    public Hotel(String name, int availableRooms) {
        this.name = name;
        this.availableRooms = availableRooms;
    }

    public synchronized boolean reserveRoom() {
        if (availableRooms > 0) {
            availableRooms--;
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }
}