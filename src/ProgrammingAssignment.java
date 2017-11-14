
import java.io.IOException;
import static java.lang.Math.E;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
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
        long startTime = System.currentTimeMillis();
        int shortest_path_touching_all_vertexes = DijkstraSumShortestPath(graph);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        double elapsedSeconds = elapsedTime / 1000.0;
        
        //print the result
        System.out.println("The shortest path touching all vertexes is summed to " + shortest_path_touching_all_vertexes + " and was found in " + elapsedSeconds + "!");

    }
    
    public static int DijkstraSumShortestPath(SimpleDirectedWeightedGraph g)
    {
        //traversed vertexes
        ArrayList traversed = new ArrayList();
        g.edgesOf("0");
        
        
        //Vertex Set
            //copy each item in the vertex set to an array
            //ArrayList graph2DList = new ArrayList();
            String[] vertexes = (String[])g.vertexSet().toArray();
        //Vertext Set - Lowest Weight
            //parallel array list with 9999 weight
            
            int[] vertexesWeight = new int[vertexes.length];
            java.util.Arrays.asList(vertexes).indexOf("0");
            for (int i = 0; i < vertexesWeight.length; i++)
            {
                vertexesWeight[i] = 9999;
            }
            //but node 0 has 0 weight
            vertexesWeight[java.util.Arrays.asList(vertexes).indexOf("0")] = 0;
        //for all vertexes in the set
        for (int i = 0; i < vertexes.length; i++)
        {
            //(start at 0, with distance 0) and its edges
            //add the current vertex to the arraylist of passed vertexes
            traversed.add(vertexes[i]);
               //go through all its edges
               for (int j = 0; j = g.edgesOf(vertexes[i]).size(); j++)
               {
                   //the distance between the starting vertex 0 and the iterated vertex
                   Set s = g.getAllEdges(vertexes[java.util.Arrays.asList(vertexes).indexOf("0")], vertexes[i]);
                   int distance = 0;
                   for (Iterator<DefaultWeightedEdge> k = s.iterator(); k.hasNext();)
                   {
                        DefaultWeightedEdge edge = k.next();
                        distance = (int) (distance + g.getEdgeWeight(edge));
                        //if the distance of the current vertex to the termating vertex is less than the current value
                    if (vertexesWeight[java.util.Arrays.asList(vertexes).indexOf(vertexes[i])] > distance)
                    {
                        //Object e = g.getEdge(vertexes[java.util.Arrays.asList(vertexes).indexOf("0")], edge);
                        //set the termating vertex's distance of the edge to the node's distance in the parallel array
                        vertexesWeight[i] = distance;
                    }
                   }
               }
               
        }
        
        //sum up all of the weights
        //*why? because we are told to take the "shortest paths from node 0 to each node" which can be interpreted as the sum of all vertex weights bc we are touching EVERY single node
        
        int grandtotal = 0;
        
        for (int i = 0; i < vertexesWeight.length; i++)
        {
            grandtotal += vertexesWeight[i];
        }
        
        return grandtotal;
    }

}