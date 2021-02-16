/*
 * Client App upon TCP
 *
 * Weiying Zhu
 *
 */ 

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.nio.file.Paths;

public class TCPClient {
    public static void main(String[] args) throws IOException {

        Socket tcpSocket = null;
        PrintWriter socketOut = null;
        BufferedReader socketIn = null;
        Scanner s = new Scanner (System.in);
		System.out.println("Host to Connect To: ");
        String hostname = s.nextLine().trim();
        String fromServer;
        String fromUser;

        while (true) {
            // start timer before creating socket object
            long StartTime = System.currentTimeMillis();
            
            try {
                tcpSocket = new Socket(hostname, 5270);
                socketOut = new PrintWriter(tcpSocket.getOutputStream(), true);
                socketIn = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
                
                // end timer after creating socket object
                long EndTime = System.currentTimeMillis();
                long rtt = EndTime - StartTime;
                
                System.out.println("RTT of Establishing TCP Connection " + rtt + "ms");
               
            } catch (UnknownHostException e) {
                System.err.println("Don't know about host: " + args[0]);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to: " + args[0]);
                System.exit(1);
            }
		    
            System.out.println("HTTP method type: "); 
            String method = s.nextLine();
            System.out.println("HTM File Name: ");
            String htm = s.nextLine();
            System.out.println("HTTP Version: ");
            String vs = s.nextLine();
            System.out.println("User Agent: ");
            String ua = s.nextLine();
            fromUser = method  + " /" + htm + " "  + vs + "\r\n" + "Host: "+ hostname + "\r\nUser-Agent: " + ua + "\r\n\r\n";

            // start timer before HTTP Query
            long Starttime = System.currentTimeMillis();
           
            socketOut.println(fromUser);
		    
			
        	if ((fromServer = socketIn.readLine()) != null)
			{
				
                String[] requestLine = fromServer.split(" ", 3);
                String responseProto = requestLine[0];
                String responseCode = requestLine[1];
                String responseMessage = requestLine[2];
                // end timer after HTTP query
                long Endtime = System.currentTimeMillis();
                long rtt2 = Endtime - Starttime;
                System.out.println("RTT of HTTP Query = " + rtt2 + "ms");
                System.out.println(fromServer);

                //read headers
                while((fromServer = socketIn.readLine()) != null){
                    System.out.println(fromServer);
                    if (fromServer.equals("")){
                        break;
                    }
                }
                if (responseCode.equals("200")){
                    //make response file
                    String path = Paths.get(".").toAbsolutePath().normalize().toString();
                    File responseFile = new File(path, htm);
                    FileWriter fileWriter = null;
                    try {
                        fileWriter = new FileWriter(responseFile);
                        int emptyCounter = 0;

                        while((fromServer = socketIn.readLine()) != null){
                        
                            if(fromServer.equals("")){
                                emptyCounter++;
                                if(emptyCounter == 4){
                                    break;
                                }
                            }
                            else{
                                for(int i = 0; i < emptyCounter; i++){
                                    fileWriter.write("\r\n");
                                }
                                fileWriter.write(fromServer);
                                emptyCounter = 0;
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            if (fileWriter != null) {
                                fileWriter.flush();
                                fileWriter.close();                 
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
			}
			else {
            System.out.println("Server replies nothing!");
            break;
			}
	    
            System.out.println("Continue? y/n: ");
		    fromUser = s.nextLine().trim();
            if (fromUser.equals("n"))
                break;
            
            socketOut.close();
            socketIn.close();
            tcpSocket.close();
        }
    }
}
