import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.IOException;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876); // Create a UDP socket on port 9876

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket); // Receive the data

                byte[] fileData = receivePacket.getData();
                int dataLength = receivePacket.getLength();

                // Save the received data to a file
                FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");
                fileOutputStream.write(fileData, 0, dataLength);
                fileOutputStream.close();

                System.out.println("File received and saved as received_file.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}