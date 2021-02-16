# LinkState (Dijkstra's Algorithm)
author: Matt Hurt
course: Spring 2020 Computer Networks CS3500
instructor: Dr. Weiying Zhu 
Metropolitan State University of Denver

LinkState uses Dijkstra's algorithm to find the least cost of routing packages to a distant end reciever.

## Usage

The purpose for the program is to prompt the user for the total number of routers in the network and 
validate to maker sure that there is more than 1 router being tested.  The topo.txt file contains the 
driver for the main method which contains all of the costs for each path.

format of costs:
<# of one router> <# of the other router> <link cost between these two routers>

## Implementation

The dijkstra() method is the main focus of this program where the distance vector and the predecessor vector
are calculated to obtain the shortest path to the recieving end.  The first version of Link state originally 
compiled the cost inputs by recieving user input from the command line via a Scanner shown below:

    ''' Scanner s = new Scanner(System.in);
        System.out.print("input for v: ");
        int v = s.nextInt();
        System.out.print(" input for e: ");
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
        dijkstra(adjacencyMatrix); '''

The output from the dijkstra() appropriately showed the outputs and the shortest path.  However, developing 
the second version by implementing a process that reads the input from the "topo.txt" file and formating the data to
pass to the dijkstra() algorithm became cumbersome.  I attempted many versions of LinkedList and ArrayList
combined with Scanner functions to read in the data and pass it to an addjececyMatrix[][] variable.  There 
is a cognative error that I am having attempting to develop this program.

## Improvements to the code

Perhaps the best thing to be done at this point is scrap the code instead of building ontop of this idea.
Explore a different means of passing vector values to the shortet path algorithm and refactor the code. 

## Assignment Directions

Part I (90%): Write a Java program to build up the forwarding table at source router V0 using the Dijkstra’s algorithm.
• The routers in the network are labeled as V0, V1, V2, …, etc (for display, such labels are required to use, please do NOT use 0, 1,
    2, 3, … etc. However, it is perfectly okay for you to use indices 0, 1, …, and n – 1 internally in your program.)

• Your routing program needs to

1.  Display a prompt message to ask the user to input the total number of routers, n, in the network. Validate n to make sure that
    it is greater than or equal to 2.

2.  Use a topo.txt file that contains costs of all links. Each line in this file provides the cost between a pair of routers as below,
    where tab (‘\t’) is used to separate the numbers in each line.
    <# of one router> <# of the other router> <link cost between these two routers>
    … … …
    where the first and the second numbers in each row need to be validated to be between 0 and n - 1, the third number needs to
    be validated to be positive. For example, for the link (V0, V3) whose cost is 10, only ONE of the following two lines needs
    to be included in the topo.txt file.
    0 3 10 // only ONE of this or the next line needs to be included in the topo.txt file
    3 0 10 // only ONE of this or the above line needs to be included in the topo.txt file
    This topo.txt file can locate in the same directory where this program runs such that a path is not needed. Display a message
    saying that in which row the first invalid number is detected, close the txt file, and KEEP asking for the name of the cost input
    file until all numbers are checked to be valid. Record the cost information in the Cost matrix.
3.  Implement the Dijsktra’s algorithm to build up the shortest-path tree rooted at source router V0. As the intermediate results, at
    the end of Initialization and each iteration of the Loop, display
        The set N’
        The set Y’
        The distance vector D, i.e., D(i) for each i between 1 and n - 1
        The predecessor vector P, i.e., p(i) for each i between 1 and n - 1
4.  Use the shortest-path tree resulted from the Dijsktra’s algorithm to build up the forwarding table for router V0. Display the
    forwarding table in the following format:
        Destination     Link
        V1              (V0, …)
        V2              (V0, …)
        …
        Vn-1            (V0, …)

Part II (10%): Test your programs on the Virtual Server cs3700a.msudenver.edu

1. MAKE a directory “HW9” under your home directory on cs3700a.msdenver.edu
2. UPLOAD and COMPILE your program under “HW9” on cs3700a.msdenver.edu
3. TEST your program running on cs3700a.msudenver.edu
4. SAVE a file named testResultsClient.txt under “HW9” on cs3700a.msudenver.edu, which captures the outputs of your program
when you test it. You can use the following command to redirect the standard output (stdout) and the standard error (stderr) to a
file on UNIX, Linux, or Mac, and view the contents of the file
java prog_name_args | tee testResultsClient.txt //copy stdout to the .txt file
//if you want, you may also use “script” command instead of the “tee” command
//to write both stdin and stdout into testResultsClient.txt while testing your
//client program on cs3700a. For how to use “script”, see
// https://www.geeksforgeeks.org/script-command-in-linux-with-examples/
//or Google “script command in Linux” if the above link is broken.
cat file-name //display the file’s contents.