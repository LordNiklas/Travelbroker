import org.zeromq.ZMQ;

public class TravelAgency {
    private ZMQ.Context context;
    private ZMQ.Socket socket;

    public TravelAgency() {
        this.context = ZMQ.context(1);
        this.socket = context.socket(ZMQ.REQ);  // TravelAgency verwendet REQUEST-Socket
    }

    public void start() {
        socket.connect("tcp://localhost:5556");  // Verbindung zum TravelBroker
        System.out.println("TravelAgency started...");
    }

    public void makeBooking(String hotelName, int week) {
        String request = "BOOK " + hotelName + " " + week;
        socket.send(request);
        String response = socket.recvStr();
        System.out.println("Response from TravelBroker: " + response);
    }
}
