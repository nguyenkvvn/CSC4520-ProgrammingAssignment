
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
    String[] AbominableOutput;

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
        //CITATION: http://www.homeandlearn.co.uk/java/read_a_textfile_in_java.html
        //FileReader fr = new FileReader(filepath);
        //BufferedReader textReader = new BufferedReader(fr);
        //end citation
        
        File file = new File(filepath);
        Scanner textReader = new Scanner(file);
        
        //scope out the number of lines
//        int linescount = 0;
//        while (textReader.readLine()!= null)
//        {
//            linescount++;
//        }
//        
//        //reload the BufferReader to grab the lines this time and dump it into an array of Strings. (All this could be avoided if the input was given as a CSV or with some sense used in the formatting.)
//        textReader = new BufferedReader(fr);
//        AbominableOutput = new String[linescount];
//        for (int i = 0; i < linescount; i++)
//        {
//            AbominableOutput[i] = textReader.readLine();
//            System.out.println("Reading " + AbominableOutput[i]);
//            
//        }
        
        int i = 0;
        while (textReader.hasNextLine())
        {
            //AbominableOutput[i] = textReader.nextLine();
            System.out.println("Reading " + textReader.nextLine());
            i++;
        }
        
        textReader = new Scanner(file);
        AbominableOutput = new String[i];
        i = 0;
        while (textReader.hasNextLine())
        {
            AbominableOutput[i] = textReader.nextLine();
            System.out.println("Adding " + AbominableOutput[i]);
            //i++;
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
        int settedVertex = -1;
        for (int i = 2; i < AbominableOutput.length; i++)
        {
            //VERTEX CONDITION
            //if the line begins with a non space (i.e, an integer)
//            if (AbominableOutput[i].charAt(0) != ' ')
//            {
//                graphfromfile.addVertex(AbominableOutput[i]);
//                settedVertex = Integer.parseInt(AbominableOutput[i]);
//                System.out.println("Added " + AbominableOutput[i] + " as a vertex.");
//            }
            
            //extract the integers out of the line
            //CITATION: https://stackoverflow.com/questions/12721261/how-to-extract-multiple-integers-from-a-string-in-java
            System.out.println("Parsing: Line " + i + " " + AbominableOutput[i]);
            Matcher m = Pattern.compile("\\d+").matcher(AbominableOutput[i]);
            settedVertex = Integer.parseInt(m.group(1));
            graphfromfile.addVertex(AbominableOutput[i]);
            System.out.println("Added " + AbominableOutput[i] + " as a vertex.");
            
            
            int vertex = Integer.parseInt(AbominableOutput[i]);
        }
        
        for (int i = 1; i < AbominableOutput.length; i++)
        {
            //VERTEX CONDITION
            //if the line begins with a non space (i.e, an integer)
            if (AbominableOutput[i].charAt(0) != ' ')
            {
                //logic reused to set the vertex for edging- no need to add again into the graph
                //graphfromfile.addVertex("Vertex " + AbominableOutput[i]);
                settedVertex = Integer.parseInt(AbominableOutput[i]);
            }
            //VERTEX EDGE CONDITION
            //if the line begins with a space
            if (AbominableOutput[i].charAt(0) == ' ')
            {
                int endVertex = -1;
                int pathWeight = -1;
                
                //extract the integers out of the line
                //CITATION: https://stackoverflow.com/questions/12721261/how-to-extract-multiple-integers-from-a-string-in-java
                Matcher m = Pattern.compile("\\d+").matcher(AbominableOutput[i]);
                while(m.find())
                {
                    //the first integer found is the end vertex
                    if (endVertex == -1)
                    {
                        endVertex = Integer.parseInt(m.group());
                    }
                    //the second (and last) integer found is the weight
                    else
                    {
                        pathWeight = Integer.parseInt(m.group());
                    }
                }
                
                DefaultWeightedEdge edge = graphfromfile.addEdge(String.valueOf(settedVertex), String.valueOf(endVertex));
                graphfromfile.setEdgeWeight(edge, pathWeight);
            }
        }
        
        return graphfromfile;
    }
    
}
