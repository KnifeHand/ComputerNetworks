import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

//******************************************************** */
public class Server {

    // CLI for sending smtp
    public static void smtp() throws IOException {

        try {
           

        }//end try
        finally{

        }
            
    }// end smtp

    //*************************************************************
    public static void rCode(BufferedReader in) {
        String url = "cs3700@msudenver.edu";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        in.close();
        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        /*JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        System.out.println("statusCode- "+myResponse.getString("statusCode"));
        System.out.println("statusMessage- "+myResponse.getString("statusMessage"));
        System.out.println("ipAddress- "+myResponse.getString("ipAddress"));
        System.out.println("countryCode- "+myResponse.getString("countryCode"));
        System.out.println("countryName- "+myResponse.getString("countryName"));
        System.out.println("regionName- "+myResponse.getString("regionName"));
        System.out.println("cityName- "+myResponse.getString("cityName"));
        System.out.println("zipCode- "+myResponse.getString("zipCode"));
        System.out.println("latitude- "+myResponse.getString("latitude"));
        System.out.println("longitude- "+myResponse.getString("longitude"));
        System.out.println("timeZone- "+myResponse.getString("timeZone")); */ 
   }
//******************************************************************* 
    public static void main(String[] args) throws Exception {
    
        ServerSocket server = new ServerSocket(8080);
        System.out.println("looking for connection");
        //Socket socket = server.accept();
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        PrintStream out = new PrintStream(output);
        String message = in.readLine();
        
        while (true){
            try(Socket socket = server.accept()){
                System.out.println("Establishing connection");
                System.out.println("Connected to client...");
                if(in = "GET"){
                    Server.rCode(in);
                }
                if(in = "POST"){
                    Server.rCode(in);
                }
                if(in = "HELO"){
                    Server.smtp();
                }
            }
            
            /*System.out.println("Message from user [" 
                + socket.getInetAddress().getHostName() + "]: " 
                + message);

                if ("exit".equals(message)){
                break;
                }*/
            out.println(message);
            in.close();
            out.close();
            socket.close();
            System.out.println("Server connection closed..");
            server.close();
            return;
        }//end while
        
        //System.out.println("Server connection terminated...");
        
        }//end main
}// end Server
  

