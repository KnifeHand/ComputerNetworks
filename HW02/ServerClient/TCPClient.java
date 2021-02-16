/*
 * Client App upon TCP
 *
 * Matt Hurt via Prof. Zhu's example
 *
 */ 

import java.io.*;
import java.net.*;
import java.util.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {

        Socket tcpSocket = null;
        PrintWriter socketOut = null;
        BufferedReader socketIn = null;
		Scanner in = new Scanner(System.in);

       	if (args.length != 1) {
        	System.out.println("Usage: Type in '<filename.java> cs3700a.msudenver.edu' to access IP or DNS");
             	return;
        }	

	System.out.print("What is the IP or DNS you wish to access: ");
	String s = in.nextLine();
	System.out.print("You entered : " + s + " is that correct?");
	String a = in.nextLine();
	while (a == ""){
		if (a == "yes"){
			return;
		}
		else {
			System.out.print("Type yes or no");
			a = in.nextLine();
		}
        }
	try {
            tcpSocket = new Socket(args[0], 5150);
            socketOut = new PrintWriter(tcpSocket.getOutputStream(), true);
            socketIn = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
        } 
	catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + args[0]);
            System.exit(1);
        } 
	catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + args[0]);
            System.exit(1);
        }

        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        while ((fromUser = sysIn.readLine()) != null) {
		System.out.println();
		System.out.println("Client: " + fromUser);
            	socketOut.println(fromUser);
				
		if ((fromServer = socketIn.readLine()) != null){
			System.out.println("Server: " + fromServer);
		}
		else {
                System.out.println("Server replies nothing!");
                //reak;
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
