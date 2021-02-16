import java.io.*;
import java.net.*;
import java.util.*;

class UDPClient {

    public UDPClient() {
        System.out.println("UDP Client Started");
        Scanner scanner = new Scanner(System.in);
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            byte[] sendMessage;
            while (true) {
                System.out.print("Enter a message: ");
                String message = scanner.nextLine();
                if ("quit".equalsIgnoreCase(message)) {
                    break;
                }
                sendMessage = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendMessage, sendMessage.length, inetAddress, 5150);
                clientSocket.send(sendPacket);

                byte[] receiveMessage = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveMessage, receiveMessage.length);
                clientSocket.receive(receivePacket);
                String receivedSentence = new Strin(receivePacket.getData());
                System.out.println("Received from server[" + receivedSentence + "]\nfrom " + receivePacket.getSocketAddress());
                System.out.println("Received from server [" + receivedSentence.trim() + "]\nfrom " + receivePacket.getSocketAddress());
            }
            clientSocket.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        System.out.println("UDP Client Terminating ");
    }
    public static void main(String [] args){
        new UDPClient();
    }
}