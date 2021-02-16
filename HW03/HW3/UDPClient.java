/*
 * Client App upon UDP
 * Weiying Zhu
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;

public class UDPClient {
  public static void main(String[] args) throws IOException {

    if (args.length != 1) {
      System.out.println("Usage: java UDPClient <hostname>");
      return;
    }

    // creat a UDP socket
    DatagramSocket udpSocket = new DatagramSocket();

    BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
    String fromServer;
    String fromUser;

    System.out.println("Item ID\t\tItem Description");
    System.out.println("00001\t\tNew Inspiron 15");
    System.out.println("00002\t\tNew Inspiron 17");
    System.out.println("00003\t\tNew Inspiron 15R");
    System.out.println("00004\t\tNew Inspiron 15z Ultrabook");
    System.out.println("00005\t\tXPS 14 Ultrabook");
    System.out.println("00006\t\tNew XPS 12 UltrabookXPS");

    System.out.println("Input item ID:\t");


    while ((fromUser = sysIn.readLine()) != null) {

      // display user input
      System.out.println("From Client: " + fromUser);

      if (fromUser.equals("Bye."))
        break;

      // start timer
      long StartTime = System.currentTimeMillis();
      
      // send request
      InetAddress address = InetAddress.getByName(args[0]);
      byte[] buf = fromUser.getBytes();
      DatagramPacket udpPacket = new DatagramPacket(buf, buf.length, address, 5270);
      udpSocket.send(udpPacket);

      // get response
      byte[] buf2 = new byte[4096];
      DatagramPacket udpPacket2 = new DatagramPacket(buf2, buf2.length);
      udpSocket.receive(udpPacket2);

      // end timer
      long EndTime = System.currentTimeMillis();
      long rtt = EndTime - StartTime;

      // display response
      fromServer = new String(udpPacket2.getData(), 0, udpPacket2.getLength());
      System.out.println("Item ID\t\tItem Description\t\tPrice\t\tInventory");
      System.out.println(fromServer);
      System.out.println("RTT of Query");
      System.out.println(rtt + " ms");
    }

    udpSocket.close();
  }
}
