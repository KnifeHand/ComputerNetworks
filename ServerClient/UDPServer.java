import java.io.*;
import java.net.*;

public class UDPServer {

    public UDPServer() {
        System.out.println("UDP Server Started");
        try (DatagramSocket serverSocket = new DatagramSocket(5150)){
            while (true) {
                byte[] receiveMessage = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveMessage, receiveMessage.length);
                serverSocket.receive(receivePacket);
                String message = new String(receivePacket.getData());
                System.out.println("Received from client: [" + message + "]\nFrom: " 
                    + receivePacket.getAddress());
                System.out.println("Received from client: [" + message.trim() + "]\nFrom: " 
                    + receivePacket.getAddress());
                InetAddress inetAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                byte[] sendMessage;
                sendMessage = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendMessage, 
                    sendMessage.length, inetAddress, port);
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        System.out.println("UDP Server Terminating");
    }
    public static void main(String[] args){
        new UDPServer();
    }
}