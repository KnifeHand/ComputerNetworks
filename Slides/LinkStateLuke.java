import java.io.IOException;
import java.util.Scanner;

public class LinkStateLuke{
    
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
                if(adjacencyMatrix[minVertex][j] != 0 && !visited[j] && distance[minVertex] != Integer.MAX_VALUE){
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

    }
    private static int findMinVertex(int[] distance, boolean visited[]){

        int minVertex = -1;
        for(int i = 0; i < distance.length; i++){
            if(!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])){
                minVertex = i;
            }
        }
        return minVertex;
    }
    public static void main(String[] args) throws IOException{

        String file_name = "C:/topo.txt";

        try{
            ReadFile file = new ReadFile(file_name);
            String[] aryLines = file.OpenFile();

            int i;
            for(i=0; i < aryLines.length; i++){
                System.out.println(aryLines[i]);
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

       /*  Scanner s = new Scanner(System.in);
       // System.out.print("input for v: ");
        int v = s.nextInt();
       // System.out.print(" input for e: ");
        int e = s.nextInt();
        int adjacencyMatrix[][] = new int[v][v];
        for(int i = 0; i < e; i++){
            int v1 = s.nextInt();
            int v2 = s.nextInt();
            int weight = s.nextInt();
            adjacencyMatrix[v1][v2] = weight;
            adjacencyMatrix[v2][v2] = weight;
        }
        s.close();
        dijkstra(adjacencyMatrix); */

    }
}