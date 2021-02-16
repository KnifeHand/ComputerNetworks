/*
 * Server App upon UDP
 * Matt Hurt
 */ 
 
import java.io.*;
import java.net.*;
import java.util.*;

public class UDPServer {

    static ArrayList<ComputerList> coms = new ArrayList<>();

    public static void processInput() {

        try {
        final String INPUT_FILE = "InventoryList.txt";
        FileInputStream inputFile = new FileInputStream(INPUT_FILE);
        Scanner scnr = new Scanner(inputFile);
        while (scnr.hasNextLine()) {
                ComputerList cl;
                String line = scnr.nextLine();
                String [] items =line.split(", ");
                cl = new ComputerList(items[0], items[1], items[2], items[3]);
                coms.add(cl);
                
        }
    }catch (IOException ex) {
            ex.printStackTrace();
    }
}
    public static void main(String[] args) throws IOException {
	 
	DatagramSocket udpServerSocket = null;
        BufferedReader in = null;
	DatagramPacket udpPacket = null, udpPacket2 = null;
	String fromClient = null, toClient = null;
        boolean morePackets = true;

	byte[] buf = new byte[256];
	
	udpServerSocket = new DatagramSocket(5150);
	processInput();	  	  
        while (morePackets) {
            try {

                // receive UDP packet from client
                udpPacket = new DatagramPacket(buf, buf.length);
                udpServerSocket.receive(udpPacket);

		fromClient = new String(udpPacket.getData(), 0, udpPacket.getLength());
							
		// get the response
		for(ComputerList c : coms){
			String temp = c.toString();
			if(temp.contains(fromClient)){
				toClient = c.toString();
		
			}
		}					 
		// send the response to the client at "address" and "port"
                InetAddress address = udpPacket.getAddress();
                int port = udpPacket.getPort();
		byte[] buf2 = toClient.getBytes();
                udpPacket2 = new DatagramPacket(buf2, buf2.length, address, port);
                udpServerSocket.send(udpPacket2);
					 
            } catch (IOException e) {
                e.printStackTrace();
					 morePackets = false;
            }
        }
		  
        udpServerSocket.close();

    }
}
