/*
 * Server App upon UDP
 * Weiying Zhu
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

public class UDPServer {
    static class ItemRecord {
        public String Description, Price, Inventory;

        public ItemRecord(String Description, String Price, String Inventory) {
            this.Description = Description;
            this.Price = Price;
            this.Inventory = Inventory;
        }
    }

    public static void main(String[] args) throws IOException {

        DatagramSocket udpServerSocket = null;
        DatagramPacket udpPacket = null, udpPacket2 = null;
        String fromClient = null, toClient = null;
        boolean morePackets = true;

        // creates table to store information
        Map<String, ItemRecord> itemTable = new HashMap<>();

        // table details. first row is Item ID.
        // second row Unit Price. third row Inventory
        itemTable.put("00001", new ItemRecord("New Inspiron 15", "$379.99", "157"));
        itemTable.put("00002", new ItemRecord("New Inspiron 17", "$449.99", "128"));
        itemTable.put("00003", new ItemRecord("New Inspiron 15R", "$549.99", "202"));
        itemTable.put("00004", new ItemRecord("New Inspiron 15z Ultrabook", "$749.99", "261"));
        itemTable.put("00005", new ItemRecord("XPS 14 Ultrabook", "$999.99", "261"));
        itemTable.put("00006", new ItemRecord("New XPS 12 UltrabookXPS", "$1199.99", "178"));

        byte[] buf = new byte[256];

        udpServerSocket = new DatagramSocket(5270);

        while (morePackets) {
            try {

                // receive UDP packet from client
                udpPacket = new DatagramPacket(buf, buf.length);
                udpServerSocket.receive(udpPacket);
                ItemRecord item;
                fromClient = new String(udpPacket.getData(), 0, udpPacket.getLength()).trim();
                if (itemTable.containsKey(fromClient)) {
                    item = itemTable.get(fromClient);

                    StringBuffer buff = new StringBuffer();
                    buff.append(fromClient);
                    buff.append("\t\t");
                    buff.append(item.Description);
                    buff.append("\t\t");
                    buff.append(item.Price);
                    buff.append("\t\t");
                    buff.append(item.Inventory);
                    buff.append("\r\n");
                    toClient = buff.toString();
                }
                else {
                    toClient = "Item not found.\r\n";
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
