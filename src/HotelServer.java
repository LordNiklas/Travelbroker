import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class HotelServer {
    private String name;
    private int availableRooms;

    public HotelServer(String name, int availableRooms) {
        this.name = name;
        this.availableRooms = availableRooms;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            System.out.println("Hotel server started for " + name + " on port " + serverSocket.getLocalPort());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleRequest(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized boolean reserveRoom() {
        if (availableRooms > 0) {
            availableRooms--;
            return true;
        }
        return false;
    }

    private void handleRequest(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String request = in.readLine();
            String response;
            if ("BOOK".equals(request) && reserveRoom()) {
                response = "SUCCESS";
            } else {
                response = "FAILURE";
            }
            out.println(response);
            System.out.println("Hotel " + name + ": " + response);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}