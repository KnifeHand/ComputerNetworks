/*
 * Server App upon TCP
 * A thread is created for each connection request from a client
 * So it can handle Multiple Client Connections at the same time
 * Matt Hurt via Professor Weiying Zhu's example. 
 */ 

import java.net.*;
import java.io.*;

public class TCPMultiServer {
    public static void main(String[] args) throws IOException {

        // Listens for a connection to be made to this socket and accepts 
        // it. The method blocks until a connection is made.
        ServerSocket serverTCPSocket = null;

        // Setting listening to true will allow the object to always listen
        // for incomming traffic.
        boolean listening = true;

        try {
        /**
         * Creates a server socket, bound to the specified port. A port number
         * of {@code 0} means that the port number is automatically
         * allocated, typically from an ephemeral port range. This port
         * number can then be retrieved by calling {@link #getLocalPort getLocalPort}
         */
            serverTCPSocket = new ServerSocket(8080);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5150.");
            System.exit(-1);
        }
        // Listens for a connection to be made to this socket and accepts 
        // it. The method blocks until a connection is made.
        while (listening){
            if(userEntry == "GET"){
                // To start a new thread, create an instance of the extending 
                // class and call start().
                new TCPMultiServerThread(serverSocket.httpReqMsg());
            }
            /*
             * Upon accepting a connection request from a client, TCP Server 
             * starts a new thread
             * designated to handle the TCP connection with this client.
             */
            //new TCPMultiServerThread(serverSocket.start());
		  }
			
        serverTCPSocket.close();
    }
}
// Building the Full Response
/* It's not possible to get the full response representation using the 
    HttpUrlConnection instance.
    However, we can build it using some of the methods that the HttpUrlConnection 
    instance offers:
*/
public class FullResponseBuilder {
    public static String getFullResponse(HttpURLConnection con) throws IOException {
        StringBuilder fullResponsebuilder = new StringBuilder();
        // read status and message by adding the response status information:
        fullResponseBuilder.append(con.getResponseCode())
            .append(" ")
            .append(con.getResponseMessage())
            .append("\n");

        // read headers by getting them using getHeaderFields() and 
        // add each of them to the StringBuilder in the format 
        // HeaderName: HeaderValues:
        con.getHeaderFields().entrySet().stream()
            .filter(entry -> entry.getKey() != null)
            .forEach(entry -> {
                fullResponseBuilder.append(entry.getKey()).append(": ");
                List headerValues = entry.getValue();
                Iterator it = headerValues.iterator();
                if(it.hasNext()) {
                    fullResponseBuilder.append(", ").append(it.next());
                    while(it.hasNext()) {
                        fullResponseBuilder.append(", ").append(it.next());
                    }
                }
                fullResponseBuilder.append(", ");
            });


        /* read response content as we did previously and append it.
        *Note: that the getFullResponse method will validate whther
        * the request was successful or not in order to decide if it 
        * needs to use con.getInputStream() or con.getErrorStream() 
        * to retrieve the request's content.
        */

        return fullResponseBuilder.toString();
       }
}
