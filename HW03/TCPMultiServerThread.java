/*
 * Server App upon TCP
 * 
 * A thread is started to handle every client TCP connection to this server
 * 
 * The server has a list of available text files that can be viewed by the client.
 * When the Connection is opened, the client sends the command 
 * "Get <file-name>".  It responds to the second with a one-line message
 * either "OK" or "ERROR".  If the message is "ok", it is followed by the 
 * contents of the file with the specified file does not exist on the server.
 * 
 * The client program works with command-line arguments.  The first argument
 * must be the name or IP address of the computer where the server is running.
 * If that is the only argument on the command line, then the client gets the
 * list of files from the server and displays it on standard output.  
 * 
 * Matt Hurt via Professor Weiying Zhu's example.
 */

import java.net.*;
import java.io.*;

public class TCPMultiServerThread extends Thread {
	// Constructors
	private Socket clientTCPSocket = null; // passes (args, <port#>)
	public TCPMultiServerThread(Socket socket) {
		super("TCPMultiServerThread");
		clientTCPSocket = socket; // user entry

	} // end TCPMultiServerThread ///

	public void run() {

		System.out.println("Connected to server...");
		
		try {
			PrintWriter cSocketOut = new PrintWriter(
				clientTCPSocket.getOutputStream(), true);
				
			BufferedReader cSocketIn = new BufferedReader(new InputStreamReader(
				clientTCPSocket.getInputStream()));	
				System.out.println("Client request: " + cSocketIn);		
			
			String fromClient;
			String toClient;

			while ((fromClient = cSocketIn.readLine()) != null) {
				System.out.println("Client input: " + fromClient);
				if(fromClient.equals("GET")){
					TCPMultiServerThread.http_request(fromClient);

				}
				// Default response
				toClient = fromClient.toUpperCase(); 
				cSocketOut.println("Your text is now uppercase: " + toClient);
				if (fromClient.equals("Bye")){
					break;
				}
			}

		   cSocketOut.close();
		   cSocketIn.close();
		   clientTCPSocket.close();

		} catch (IOException e) {
		    e.printStackTrace();
		} catch (Exception e){
			System.out.println(
				"Sorry, an error occurred while reading data from the server.");
			System.out.println("Error: " + e);
		}
	}


	// Process file using a parse method to return the respnose
	// TODO: "parse cannot be resolved to a field".
	/*public String processFile(String file_message) throws IOException {
		FileInputStream incoming_File = new FileInputStream(file_message);
		String[] parse;
			parse = file_message.split("\\s+");
			String user_file = incoming_File.parse[1].replace("/","");
			File file_to_read = new File(user_file);
			BufferedReader incomingFile = new BufferedReader(new FileReader(file_to_read));
				try{
								
					while((fb = incomingFile.readLine()) != null){
						file_content = file_content + file_body + "\n";
						incomingFile.close();
						return;
					}
					incomingFile.close();
				}catch(IOException e){
					e.printStackTrace();
				}
				incomingFile.close();
				cSocketOut.println(file_content);
				incomingFile.close();
		return null;
		
	}*/

	public static void http_request(String client_request) throws Exception {
		String[] http;
		http = client_request.split(" ");
		String url = http[1];
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// Default is GET
		con.setRequestMethod("GET");

        // ser the request method and properties.
        con.setRequestMethod("GET");
		con.setRequestProperty("Date:", "Server");
		int responseCode = con.getResponseCode();
		System.out.println("Connecting to url: " + url);
		System.out.println("GET Response Code : " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK){
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Print results
			System.out.println(response.toString());
		}else {
			System.out.println("GET request failed....");
		}
		


        
    }
}
