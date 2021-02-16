/*  Homework assignment 3
*   by Matt Hurt
*   
*   Simplified HTTP on TCP
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer{

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(80);
        System.out.println("Listening for connection on port 80 ....");
        while (true){
            // start accepting connections incoming connection by blocking call to accept() method.
            Socket clientSocket = server.accept();
            //1.  Read HTTP request from the client socket
            InputStreamReader strRead = new InputStreamReader(clientSocket.getInputStream());
            //2.  Prepare an HTTP response
            BufferedReader reader = new BufferedReader(strRead);
            //3.  Send HTTP response to the client
            String line = reader.readLine();
            while (!line.isEmpty()) {
                System.out.println(line);
                line = reader.readLine();
            }
            //4.  Close the socket

            
        }
    }
}