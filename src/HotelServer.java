import org.zeromq.ZMQ;

public class HotelServer {
    private String name;
    private int availableRooms;
    private ZMQ.Context context;
    private ZMQ.Socket socket;

    public HotelServer(String name, int availableRooms) {
        this.name = name;
        this.availableRooms = availableRooms;
        this.context = ZMQ.context(1);
        this.socket = context.socket(ZMQ.REP);  // HotelServer verwendet REPLY-Socket
    }

    public void start() {
        socket.bind("tcp://localhost:" + (5000 + availableRooms));  // Port für jedes Hotel
        System.out.println("Hotel server started for " + name + " on port " + (5000 + availableRooms));

        while (true) {
            String request = socket.recvStr();
            if (request != null) {
                String[] parts = request.split(" ");
                String action = parts[0];
                String hotelName = parts[1];
                int week = Integer.parseInt(parts[2]);

                String response;
                if ("BOOK".equals(action) && reserveRoom()) {
                    response = "SUCCESS";
                } else {
                    response = "FAILURE";
                }
                socket.send(response);
                System.out.println("Hotel " + name + ": " + response);
            }
        }
    }

    private synchronized boolean reserveRoom() {
        if (availableRooms > 0) {
            availableRooms--;
            return true;
        }
        return false;
    }
}
