public class Http {
    
    // Constructor
    public Http(){

    }

    public void http_request(String client_request) throws Exception {
		URL obj = new URL(url);
		String url;
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