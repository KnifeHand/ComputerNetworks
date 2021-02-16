import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LinkState {

    Scanner s;

    // Utility function to add an edge in an undireted graph
    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }   //end addEdge

    public static void dijkstra(int[][] adjacencyMatrix) {
        //Initialization
        int v = adjacencyMatrix.length;
        boolean visited[] = new boolean[v];
        int distance[] = new int[v];
        int pred[] = new int[v];
        pred[0] = -1;

        distance[0] = 0;
        for (int i = 1; i < v; i++){
            distance[i] = Integer.MAX_VALUE;
            pred[i] = -1;

        } 
        for(int i = 0; i < v - 1; i++){ // Adjacency list.
            // Find Vertex with Min distance
            int minVertex = findMinVertex(distance, visited); // Priority Queue.
            visited[minVertex] = true;
            // Explore neighbors

            for(int j = 0; j< v; j++){
                if(adjacencyMatrix[minVertex][j] != 0 
                    && !visited[j] && distance[minVertex] != Integer.MAX_VALUE){

                    int newDist = distance[minVertex] + adjacencyMatrix[minVertex][j];
                    if(newDist < distance[j]){
                        distance[j] = newDist;
                    }
                }
            }
        }
        //Print
        for(int i = 0; i < v; i++){
            System.out.println(i + " "+ distance[i]);
        }
    }// end dijkstra

    public static void display(ArrayList<ArrayList<Integer>> adj) {
        for(int i = 0; i < adj.size(); i++){
            System.out.println("\nAdjcency list of vertex" + i);
            for(int j = 0; j < adj.get(i).size(); j++){
                System.out.print(" -> " + adj.get(i).get(j));
            }
            System.out.println();
        }
    }// end display

    private static int findMinVertex(int[] distance, boolean visited[]){

        int minVertex = -1;
        for(int i = 0; i < distance.length; i++){
            if(!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])){
                minVertex = i;
            }
        }
        return minVertex;
    }// end findMinVertex

    public void pullFile() throws FileNotFoundException{
        
        File text = new File("topo.txt");
        Scanner s = new Scanner(text);
        int adjacencyMatrix[][] = null;

        int row = 0, a, b, c; // temp variable to convert String to int
        int lineNumber = 1; // line counter for test

        while(null != lineFromFile <= s.nextLine()){
            text = s.useDelimiter(".").next();
            String line = s.nextLine(); // test to see if reading properly
            System.out.println("line " + lineNumber + " :" + line); // print to screen state to view output
            lineNumber++; // keep adding row numbers till the end of the document

            String[] temp = lineFromFile.split('\t'); // convert string to int
            a = Integer.parseInt(temp[1]);
            b = Integer.parseInt(temp[2]);
            c = Integer.parseInt(temp[3]);

            // looking for 3 or more inputs to calculate vector    
            if(temp.length < 3){
                System.out.println("Missing values in row: " + row);
                handleBadInput();
            }
            // if the vector does not fall within the range
            else if(a > (v-1) || a < 0 || b > (v-1) || b < 0){
                System.out.println("Router number out of range on row: " + row);
                // handleBadInput();
                row = 0;
            }
            // if no errors  
            else {
                adjacencyMatrix[a][b] = temp[c];
                
            } 
        }
    }// end pullFile

    public static int main(String[] args) throws IOException {

        int v = adjacencyMatrix[][].pullFile();
        int e = val;
        int adjacencyMatrix[][] = new int[v][v];
        adjacencyMatrix.pullFile(); // Cannot invoke pullFile() on array type int[][]
        
        for(int i = 0; i < e; i++){
            int v1 = val; //FIXME: cannot find symbol
            int v2 = val;//FIXME: cannot find symbol
            int weight = val;//FIXME: cannot find symbol
            adjacencyMatrix[v1][v2] = weight;
            adjacencyMatrix[v2][v2] = weight;
        } 
        
        //s.close();
        dijkstra(adjacencyMatrix);
       
    }// end main
    
    
}// end LinkState class