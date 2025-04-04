import org.zeromq.ZMQ;

import java.util.List;

public class TravelBroker {
    private ZMQ.Context context;
    private ZMQ.Socket socket;
    private List<String> hotelAddresses;

    public TravelBroker(List<String> hotelAddresses) {
        this.context = ZMQ.context(1);
        this.socket = context.socket(ZMQ.REQ);  // TravelBroker verwendet REQUEST-Socket
        this.hotelAddresses = hotelAddresses;
    }

    public void start() {
        socket.bind("tcp://*:5555");  // Port für den TravelBroker
        System.out.println("TravelBroker started on port 5555");
    }

    public boolean requestBooking(String hotelName, int week) {
        for (String hotelAddress : hotelAddresses) {
            socket.connect("tcp://" + hotelAddress);  // Verbindet sich mit jedem Hotel

            String message = "BOOK " + hotelName + " " + week;
            socket.send(message);

            String response = socket.recvStr();
            if ("SUCCESS".equals(response)) {
                System.out.println("Booking successful for " + hotelName + " for week " + week);
                return true;
            } else {
                System.out.println("Booking failed for " + hotelName + " for week " + week);
            }
        }
        return false;
    }
}
