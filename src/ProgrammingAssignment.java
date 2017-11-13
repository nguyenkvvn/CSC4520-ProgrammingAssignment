
import java.io.IOException;
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

    }

}