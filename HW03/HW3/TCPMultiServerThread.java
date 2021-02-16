/*
 * Server App upon TCP
 * A thread is started to handle every client TCP connection to this server
 * Weiying Zhu
 */

import java.net.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.nio.file.Paths;

public class TCPMultiServerThread extends Thread {
    private Socket clientTCPSocket = null;

    public TCPMultiServerThread(Socket socket) {
        super("TCPMultiServerThread");
        clientTCPSocket = socket;
    }

    public void run() {

        try {
            PrintWriter cSocketOut = new PrintWriter(clientTCPSocket.getOutputStream(), true);
            BufferedReader cSocketIn = new BufferedReader(new InputStreamReader(clientTCPSocket.getInputStream()));

            ArrayList<String> fromClient = new ArrayList<>(), toClient = new ArrayList<>();
            String fromClientLine;
            while ((fromClientLine = cSocketIn.readLine()) != null) {
                fromClient.add(fromClientLine);
                System.out.println(fromClientLine);
                if(fromClientLine.equals("")){
                    break;
                }
            }

            String[] requestLine = fromClient.get(0).split(" ", 3);
            String requestMethod = requestLine[0];
            String requestURL = requestLine[1];
            String requestProto = requestLine[2];
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

            Date currentTime = new Date();
            String currentTimeFomatted = dateFormat.format(currentTime);

            if (requestMethod.equals("GET")) {
                String path = Paths.get(".").toAbsolutePath().normalize().toString();
                File requestFile = new File(path, requestURL);
                if (requestFile.exists()) {
                    toClient.add(requestProto + " 200 OK\r\n");
                    toClient.add("Date: " + currentTimeFomatted + "\r\n");
                    toClient.add("Server: Spot\r\n");
                    toClient.add("\r\n");
                    Scanner myReader = new Scanner(requestFile);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        toClient.add(data);
                    }
                    myReader.close();

                } else {
                    toClient.add(requestProto + " 404 Not Found\r\n");
                    toClient.add("Date: " + currentTimeFomatted + "\r\n");
                    toClient.add("Server: Spot\r\n");
                    toClient.add("\r\n");

                }

            } else {
                toClient.add(requestProto + " 400 Bad Request\r\n");
                toClient.add("Date: " + currentTimeFomatted + "\r\n");
                toClient.add("Server: Spot\r\n");
                toClient.add("\r\n");
            }
            toClient.add("\r\n");
            toClient.add("\r\n");
            toClient.add("\r\n");
            toClient.add("\r\n");

            for (String line : toClient) {
                cSocketOut.print(line);

            }

            cSocketOut.close();
            cSocketIn.close();
            clientTCPSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}