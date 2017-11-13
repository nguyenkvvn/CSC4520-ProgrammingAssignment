
import java.io.IOException;
import org.jgraph.graph.Edge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

/*

Programming Assignment
CSC 4520 - Djistkra's Algorithm

Kevin Vinh Nguyen
11/12/17

Original Character Do Not Steal

*/

public class ProgrammingAssignment
{


    //the main method that runs when the application begins
    public static void main(String[] args) throws IOException, Exception
    {
        //ask for the input
        System.out.println("Please give the ABSOLUTE path to the input file.");
        java.util.Scanner keyboardIn = new java.util.Scanner(System.in);
        String filepath = keyboardIn.nextLine();
        
        //consume the input
        PoorlyFormattedInputParser parser = new PoorlyFormattedInputParser(filepath); 
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = parser.makeGraph();
        
        //begin the timer when computing hte algorithm, not when reading the abominable file
        
        //

    }
    
    public static int DijkstraSumShortestPath(SimpleDirectedWeightedGraph g)
    {
        //traversed vertexes
        ArrayList
        g.edgesOf("0");
        g.vertexSet();
        
        //Vertex Set
            //copy each item in the vertex set to an ArrayList
        //Vertext Set - Lowest Weight
            //parallel array list with 9999 weight
            //but node 0 has 0 weight
        
        //for all vertexes in the set
            //for the current node (start at 0, with distance 0) and its edges
               //go through all its edges
               //if the distance of the current vertex to the 
                //set the termating vertex's distance of the edge to the node's distance
                
        //sum up all of the weights
        //*why? because we are told to take the "shortest paths from node 0 to each node" which can be interpreted as the sum of all vertex weights bc we are touching EVERY single node
        return 0;
    }

}