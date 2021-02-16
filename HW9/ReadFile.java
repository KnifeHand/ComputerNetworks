import java.io.*;

public class ReadFile {

    private String path;

    public ReadFile(String file_path){
        path = file_path;
    }

    public String[] OpenFile() throws IOException{

        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);

        int numberOfLines = 7;
        String[] textData = new String[numberOfLines];

        int i;

        for(i = 0; i < numberOfLines; i++){
            textData[i] = textReader.readLine();
        }

        textReader.close();
        return textData;
    }

    int readLines() throws IOException {
        FileReader file_to_read = new FileReader(path);
        BufferedReader bf = new BufferedReader(file_to_read);

        String aLine;
        int numberOfLines = 0;

        while ((aLine = bf.readLine()) != null){
            numberOfLines++;
        }
        bf.close();
        return numberOfLines;
    }
    public static void main(String[] args){
         
        /*  String file_name = "topo.txt";
         ReadFile file = new ReadFile(file_name);
         String[] aryLines = file.OpenFile();
         try{
             int i;
             int val = 0;
             for(i=0; i < aryLines.length; i++){
                 System.out.println(aryLines[i]);
                 try{ // attempt to turn string into integer
                     val = Integer.parseUnsignedInt(aryLines); // FIXME: incompatible types: String[] cannot be converted
                                                               // to String
                 // thrown when String contains characters other than a digit
                 } catch(NumberFormatException e){  
                     System.out.println("Invalid String");
                 }
                 return val; //FIXME: incompatible type: unexpected return value
             }
         } catch(IOException e){
             System.out.println(e.getMessage());
         }
         */ //return aryLines;  //FIXME: incompatible type: unexpected return value
        
        
        // System.out.print("How many routers and nodes?: ");
        
    }

}