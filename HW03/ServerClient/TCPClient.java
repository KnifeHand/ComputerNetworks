/*
 * Client App upon TCP
 *
 * Matt Hurt via Professor Weiying Zhu's example.
 *
 */

import java.io.*;
import java.net.*;
import java.lang.*;
import java.net.NetworkClient;
// import java.util.Scanner;

import sun.net.www.http.HttpClient;

public class TCPClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        
        
        // Verify if hostname is attached
        if (args.length != 1) {
            
            System.out.println("Usage: 'java TCPClient <localhost>' "); // TODO: CHANGE TO Client AND REFACTOR.
            return;
        }
        // Make the connection between the client and the server
        try {
            host = args[0];
            socketAddress = args[1];
            Socket tcpSocket = new Socket(host, socketAddress);
            // Create new print writer to write the ... outputsream and autoflush
            PrintWriter socketOut = new PrintWriter(tcpSocket.getOutputStream(), true);
            BufferedReader socketIn = null;
            InputStream incomming;
            // Reader is created to read from the incomming message stream from the server
            // using the to pass the socket ipAddress and port number.
            socketIn = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + args[0]);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + args[0]);
            System.exit(1);
        } catch(IllegalArgumentException e){
            System.err.println(e);
            System.exit(1);
        }


        // Create constants that enable the user to make requests
        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        
        
        // CLIENT CLI
        while ((fromUser = sysIn.readLine()) != "exit") {
            if (socketAddress.isConnected() == true) {
                System.out.println("Connection established...");
            }
            System.out.print("****Welcome to the Client Interface****");
            System.out.print("Type in the command HELO: ");
            Scanner read = read(fromuser);
            connect(socketAddress, 150000); // Send the command to the Server            			
                if ((fromServer = socketIn.readLine()) != null){ 
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

    private static void socketOut(String fromUser) {
    }
}
