import java.net.*;
import java.io.*;


public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + serverSocket);
            System.exit(-1);
        }

        while (listening){
	    		new ThreadServer(serverSocket.accept()).start();
		  }
			
        serverSocket.close();
    }
}