import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Scanner;

public class ThreadServer extends Thread {
    private Socket clientTCPSocket = null;

    public ThreadServer(Socket socket) {
		super("ThreadServer");
		clientTCPSocket = socket;
	}
	
    public void run() {

		try {
	 	   	PrintWriter cSocketOut = new PrintWriter(clientTCPSocket.getOutputStream(), true);
	  		 BufferedReader cSocketIn = new BufferedReader(
				    new InputStreamReader(clientTCPSocket.getInputStream()));

			String fromClient, toClient, host, line = null, cmd;
			int port;

			StringBuilder sb = new StringBuilder();
			while((line = cSocketIn.readLine()) != "bye"){
				sb.append(line + "/n");
				
			}
			cmd = sb.toString();
			
			
			
			toClient = "Type in the HELO command to start email application.";	
				cSocketOut.println(toClient);
				cSocketOut.flush();
			
			// toClient = "Port Number?: ";
			// 	cSocketOut.println(toClient);
			// 	cSocketOut.flush();
			
			// toClient = "Host?: ";
			
		
			while (sb != null) {
					if(cmd.equals("HELO")){
						ThreadServer.sendEmail(cmd, 8080, "mhurt@msudenver.edu");
					}
					if (cmd.equals("Bye"))
						break;
			}// end while
				
			cSocketOut.close();
			cSocketIn.close();
			clientTCPSocket.close();
			

		} catch (IOException e) {
				e.printStackTrace();
			}
	}// end run

	//************************************************ */
	public static void sendEmail(String command, int portNum, String hostName) throws IOException {
		Socket smtpSocket = null;
		// Creates a new data output stream to write data to the specified output
		// stream.
		DataOutputStream os = null;
		DataInputStream is = null;
		long eetop = 0;

		Date dDate = new Date(eetop);
		DateFormat dFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.US);

		try { // Open port to server
			String m_sHostName = hostName;
			int m_iPort = portNum;
			smtpSocket = new Socket(m_sHostName, m_iPort);
			os = new DataOutputStream(smtpSocket.getOutputStream());
			is = new DataInputStream(smtpSocket.getInputStream());

			if (command != null && os != null && is != null) { // Connection was made. Socket is ready for use.
				// [ Code to send email will be placed in here. ]
				/**
				 * Writes out the string to the underlying output stream as a sequence of bytes.
				 * Each character in the string is written out, in sequence, by discarding its
				 * high eight bits. If no exception is thrown, the counter <code>written</code>
				 * is incremented by the length of <code>s</code>.
				 */
				try {
					// Now send the email off and check the server reply.
					// Was an OK is reached you are complete.

					while (command != null) { // System.out.println(responseline);
						/* if(responseline.indexOf("Ok") != -1){
							break;
						} */
					   
						os.writeBytes("HELO\r\n");
						// You will add the email address that the server 
						// you are using know you as.
						os.writeBytes("MAIL From: <you@yourcompany.com>\r\n");
								
						// Who the email is going to.
						os.writeBytes("RCPT To: <theperson@theircompany.com>\r\n");
						//IF you want to send a CC then you will have to add this
						os.writeBytes("RCPT Cc: <theCC@anycompany.com>\r\n");

								
						// Now we are ready to add the message and the 
						// header of the email to be sent out.                
						os.writeBytes("DATA\r\n");
									
						os.writeBytes("X-Mailer: Via Java\r\n");
						os.writeBytes("DATE: " + dFormat.format(dDate) + "\r\n");
						os.writeBytes("From: Me <me@mycompany.com>\r\n");
						os.writeBytes("To:  YOU <you@yourcompany.com>\r\n");
								
						//Again if you want to send a CC then add this.
						os.writeBytes("Cc: CCDUDE <CCPerson@theircompany.com>\r\n");
									
						//Here you can now add a BCC to the message as well
						os.writeBytes("RCPT Bcc: BCCDude<BCC@invisiblecompany.com>\r\n");
						// Now send the email off and check the server reply.  
						// Was an OK is reached you are complete.
						String sMessage = "Your subjectline.";

						os.writeBytes("Subject: Your subjectline here\r\n");
						os.writeBytes(sMessage + "\r\n");
						os.writeBytes("\r\n.\r\n");
						os.writeBytes("QUIT\r\n");
					} // end while
					
				}// end try
				finally{

				}
				
			}// end if
		
		
		}// end Try
		catch(Exception ex){ 
			} // end catch
	} // end sendEmail
}// end ThreaServer.java
 
	

	
