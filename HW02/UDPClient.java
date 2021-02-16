/*
 * Client App upon UDP
 * Matt Hurt via Professor Zhu's example code
 */ 

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPClient {

    public static void itemsList() {
	System.out.println( "\n00001 New Inspiron 15"
	+ "\n00002 New Inspiron 17\n00003 New Inspiron 15R" 
	+ "\n00004 New Inspiron 15z Ultrabook\n00005 XPS 14 Ultrabook" 
	+ "\n00006 New XPS 12 UltrabookXPS ");    

    }
    public static void main(String[] args) throws IOException {

        System.out.println("Please type in the DNS or IP address you wish to access");
	Scanner user = new Scanner(System.in);
	String addr = user.nextLine();
	
        // create a UDP socket
        DatagramSocket udpSocket = new DatagramSocket();

        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        //String fromUser = " ";
	String idChoice = " ";

        while ((idChoice = sysIn.readLine()) != null) {
		itemsList();		
		System.out.print("Please choose the itemID you wish to display: ");
		Scanner scnr = new Scanner(System.in);
		idChoice = scnr.nextLine();
	  	
		if (idChoice.equals("bye")){ // || fromUser.equals("Bye")){
    				break;
	 	 	}

	  //display user input
          System.out.println("From Client: " + idChoice);
			 
          // send request
          InetAddress address = InetAddress.getByName(addr);
			 byte[] buf = idChoice.getBytes();
          DatagramPacket udpPacket = new DatagramPacket(buf, buf.length, address, 5150);
          udpSocket.send(udpPacket);
    
          // get response
		    byte[] buf2 = new byte[256];
          DatagramPacket udpPacket2 = new DatagramPacket(buf2, buf2.length);
          udpSocket.receive(udpPacket2);

  	  // display response
          fromServer = new String(udpPacket2.getData(), 0, udpPacket2.getLength());
          System.out.println("From Server: " + fromServer);
			 
				
	}
		  
        udpSocket.close();
    }
}
