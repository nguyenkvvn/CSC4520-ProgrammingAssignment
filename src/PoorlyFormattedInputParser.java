
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class PoorlyFormattedInputParser
{
    String filepath;
    //String[] AbominableOutput;
    
    ArrayList<String> AbominableOutput;

    public PoorlyFormattedInputParser()
    {
        filepath = "";
    }
    
    public PoorlyFormattedInputParser(String fp) throws IOException
    {
        loadFile(fp);
    }
    
    
    public void loadFile(String fp) throws IOException
    {
        filepath = fp;
        
        File file = new File(filepath);
        Scanner textReader = new Scanner(file);
        
//        int i = 0;
//        while (textReader.hasNextLine())
//        {
//            //AbominableOutput[i] = textReader.nextLine();
//            System.out.println("Reading " + textReader.nextLine());
//            i++;
//        }
        
        //textReader = new Scanner(file);
        AbominableOutput = new ArrayList();
        int i = 0;
        while (textReader.hasNextLine())
        {
            AbominableOutput.add(textReader.nextLine());
            //System.out.println("Adding " + AbominableOutput.get(i));
            i++;
        }
        textReader.close();
    }
    
    //CITATION: https://stackoverflow.com/questions/20246409/how-to-inlcude-weight-in-edge-of-graph
    //Citation used for how to implement JGraphT Graph representations
    public SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> makeGraph() throws Exception
    {
        //if the user has not loaded *AND* parsed the file yet, throw an exception
        if (AbominableOutput == null)
        {
            System.out.println("Oops! You need to run loadFile() first!");
            throw new Exception();
        }
        
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>  graphfromfile = 
            new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>
            (DefaultWeightedEdge.class); 
        
        //LOGIC FOR PARSING STRINGS
        //ignore the first line
        //Adding the vertices first so that no errors occure when defining the edges
        String settedVertex = "";
        for (int i = 1; i < AbominableOutput.size(); i++)
        {
            //System.out.println("Parsing Line " + i + ": " + AbominableOutput.get(i));
            //VERTEX CONDITION
            //if the line begins with a non space (i.e, an integer)
            if (!AbominableOutput.get(i).isEmpty())
            {
                if (AbominableOutput.get(i).charAt(0) != ' ')
                {
                    graphfromfile.addVertex(AbominableOutput.get(i));
                    settedVertex = AbominableOutput.get(i);
                    //System.out.println("Added " + AbominableOutput.get(i) + " as a vertex.");
                }
            }
            else
            {
                //System.out.println("Parsing Line " + i + ": was am empty line!");
            }
            
            
//            //extract the integers out of the line
//            //CITATION: https://stackoverflow.com/questions/12721261/how-to-extract-multiple-integers-from-a-string-in-java
//            System.out.println("Parsing] Line " + i + ": " + AbominableOutput.get(i));
//            Matcher m = Pattern.compile("\\d+/g").matcher(AbominableOutput.get(i));
//            try
//            {
//                System.out.println(m.group());
//                //settedVertex = Integer.parseInt(m.group());
//                graphfromfile.addVertex(AbominableOutput.get(i));
//                System.out.println("Added] " + AbominableOutput.get(i) + " as a vertex.");
//               // int vertex = Integer.parseInt(AbominableOutput.get(i));
//            }
//            catch (Exception e)
//            {
//                //do nothing if there isn't a match.
//                System.out.println("Line " + i + ": " + AbominableOutput.get(i) + " was empty.");
//            }
        }
        
        for (int i = 1; i < AbominableOutput.size(); i++)
        {
            //VERTEX CONDITION
            //if the line begins with a non space (i.e, an integer)
            if (AbominableOutput.get(i).charAt(0) != ' ')
            {
                //logic reused to set the vertex for edging- no need to add again into the graph
                //graphfromfile.addVertex("Vertex " + AbominableOutput[i]);
                settedVertex = AbominableOutput.get(i);
            }
            //VERTEX EDGE CONDITION
            //if the line begins with a space
            if (AbominableOutput.get(i).charAt(0) == ' ')
            {
                String endVertex = "";
                int pathWeight = -1;
                
                //extract the integers out of the line
                //CITATION: https://stackoverflow.com/questions/12721261/how-to-extract-multiple-integers-from-a-string-in-java
                Matcher m = Pattern.compile("\\d+").matcher(AbominableOutput.get(i));
                System.out.println(i + " " + AbominableOutput.get(i));
                while(m.find())
                {
                    //the first integer found is the end vertex
                    if (endVertex.isEmpty())
                    {
                        endVertex = m.group(0);
                    }
                    //the second (and last) integer found is the weight
                    else
                    {
                        pathWeight = Integer.parseInt(m.group(0));
                    }
                }
                
                DefaultWeightedEdge edge = graphfromfile.addEdge(String.valueOf(settedVertex), String.valueOf(endVertex));
                graphfromfile.setEdgeWeight(edge, pathWeight);
            }
        }
        
        return graphfromfile;
    }
    
}
