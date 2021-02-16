/*
 * Multiple threads are used by TCP server to support multiple clients
 * Upon accepting a connection request from a client, TCP Server starts a new thread
 * designated to handle the TCP connection with this client.
 */ 

import java.net.*;
import java.io.*;
// One of the two ways to start a new thread in Java.
 // - Extend the Thread class:
public class TCPMultiServerThread extends Thread {
	String fromClient, toClient; // create objects for handling
	// Private variable that cannot change the socket port number.
    private Socket clientTCPSocket = null;

	// This method is turned into a variable to read and write to the client 
	// using the Socket class.  
    public TCPMultiServerThread(Socket socket) {
		// Each thread may have a name, in the constructor of the extending class.
		super("TCPMultiServerThread");
		clientTCPSocket = socket;
    }

	public void rtt(){

	}
	

	public void httpReqMsg(){
		URL url = new URL("http://example.com");
		HTTPURLConnection connect = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		Mat
		

	}
	// The extending class must override the run() method of the Thread class, which is the
	// entry point for the new thread.
	// This new thread stops at the end of the run() method.
    public void run() {

		try {
	 	   PrintWriter cSocketOut = new PrintWriter(clientTCPSocket.getOutputStream(), true);
	  		BufferedReader cSocketIn = new BufferedReader(
				    new InputStreamReader(
				    clientTCPSocket.getInputStream()));

	      
			  
	 	   while ((fromClient = cSocketIn.readLine()) != null) {
				
				toClient = fromClient.toUpperCase();
				cSocketOut.println(toClient);
				
				if (fromClient.equals("Bye"))
				    break;
	 	   }
			
		   cSocketOut.close();
		   cSocketIn.close();
		   clientTCPSocket.close();

		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	
}
public class ParameterStringBuilder{
	public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException{
		StringBuilder result = new StringBuilder();
		for (Map.Entry<String,String> entry : params.entrySet()){
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}
		String resultString = result.toString();
		return resultString.length() > 0
			? resultString.substring(0, resultString.length() -1) : resultString;
	}
}