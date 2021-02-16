/*
 * Client App upon TCP
 *
 * Matt Hurt via Professor Weiying Zhu's example.
 *
 */

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        Socket tcpSocket = null;
        PrintWriter socketOut = null;
        BufferedReader socketIn = null;

        // Verify if hostname is attached
        if (args.length != 1 ) {
            System.out.print("Welcome to the Client");
            System.out.println("Usage: java TCPClient <DNS name/ip of your HTTP server>");
            return;
        }
        // Make the connection between the client and the server
        try {
            tcpSocket = new Socket(args[0], 5150);
            socketOut = new PrintWriter(tcpSocket.getOutputStream(), true);
            socketIn = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + args[0]);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + args[0]);
            System.exit(1);
        }
        // Create constants that enable the user to make requests
        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        
        // CLIENT CLI
        while ((fromUser = sysIn.readLine()) != null) {
            System.out.println("Input the DNS name/ip of your HTTP server: ");
            System.out.println("Client: " + fromUser);
            socketOut.println(fromUser);
            				
			    if ((fromServer = socketIn.readLine()) != null) {
					System.out.println("Server: " + fromServer);
                }
				else {
                System.out.println("Server replies nothing!");
                break;
				}
		    
			   if (fromUser.equals("Bye."))
					break;
         
        }

        socketOut.close();
        socketIn.close();
        sysIn.close();
        tcpSocket.close();
    }

}
