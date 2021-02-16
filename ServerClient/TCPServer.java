/*
 * Server App upon TCP
 */ 

import java.net.*;
import java.text.*;
import java.io.*;
import java.util.*;

public class TCPServer {

    public static void main(String[] args) throws IOException {
	// Try Catch for correct port send and recieve.
        ServerSocket serverTcpSocket = null;
        try {
            serverTcpSocket = new ServerSocket(5050);
        } catch (IOException e) {
            //System.err.println("Could not listen on port: " + serverTcpSocket);
            e.printStackTrace();
            System.exit(1);
        }
	
	// Try Catch if handshake did not take place.
        Socket clientTcpSocket = null;
        try {
            clientTcpSocket = serverTcpSocket.accept();

            System.out.println("A new client is connected: " + clientTcpSocket); 
        } 
	    catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter cTcpSocketOut = new PrintWriter(clientTcpSocket.getOutputStream(), true);
        BufferedReader cTcpSocketIn = new BufferedReader(new InputStreamReader(clientTcpSocket.getInputStream()));
	    String fromClient = null, toClient = null;	 
        
        while ((fromClient = cTcpSocketIn.readLine()) != null){
            Socket s = null;
            try{
                // socke object to receive incoming client requests
                s = serverTcpSocket.accept();

                System.out.println("A new client is connected: " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            // create a new thread object
            Thread t = new ClientHandler(s, dis, dos);

            // Invoke the start method
            t.start();
            
            //System.out.print("hello");		
            //cTcpSocketOut.println(toClient);
            }catch(Exception e){
                s.close();
                e.printStackTrace();
            }
            
	 
        	if (fromClient.equals("Bye.")){
                break;
		    }
        }
		  
        cTcpSocketOut.close();
        cTcpSocketIn.close();
        clientTcpSocket.close();
        serverTcpSocket.close();
    }
	 
	 

// ClientHandler class
class ClientHandler extends Thread{

    String myDate;
    
    String myString = DateFormat.getDateInstance().format(myDate);

    DateFormat fordate = new SimpleDateFormat ("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat ("hh:mm:ss");
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos){
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run(){
        String received;
        String toreturn;

        while(true){
            try{
                // Ask user what they want
                dos.writeUTF("What do you want[Date | Time]...\n" + "Type Exit to terminate connection" );

                // receive the answer from client
                received = dis.readUTF();

                if (received.equals("Exit")){
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }
            Date date = new Date();

                // write on output stream based on the answer from the client
                switch(received){
                    case "Date" :
                        toreturn = fordate.format(date);
                        dos.writeUTF(toreturn);
                        break;
                    
                    case "Time" :
                        toreturn = fortime.format(date);
                        dos.writeUTF(toreturn);
                        break;

                    default :
                        dos.writeUTF("Invalid Input");
                        break;    
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        try{
            // closing resources
            this.dis.close();
            this.dos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
}