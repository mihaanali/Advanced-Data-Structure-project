import NodeElements.KeyValPair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class treesearch 
{
	// Main method for the program
	public static void main(String[] args)throws IOException 
    {

        try {

            	// Reading commands line by line from input.txt file
            	BufferedReader br = new BufferedReader(new FileReader(args[0]));
            	String line = br.readLine();

            	// Sending order value to BplusTree class for initialization
            	int m = Integer.parseInt(line);
            	BPlusTree tree = new BPlusTree(m);

            	//  Write to output file the results of the operations.
            	PrintWriter wr = new PrintWriter("output_file.txt", "UTF-8");
            	while (true) 
            	{
            		line = br.readLine();
            		if (line == null) 
            			break;
            		String message = PerformOperation(tree, line);
            		if (message != null) 
            		{
            			System.out.println(message);
            			wr.println(message);
            		}
            	}

            	wr.close();
            	br.close();

        	} 
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    // Perform the respective function as per needed and return output message 
    private static String PerformOperation(BPlusTree tree, String line) 
    {

        // Splitting the line for gathering values
        int i = line.indexOf('(');
        int j = line.indexOf(',');
        int k = line.indexOf(')');

        // Finding what are methods and what are parameters
        String method = line.substring(0, i);
        double key; 	//  Our first key
        String val; 	// For RangeSearch val is Key, For Insert val is String

        if (j == -1) 
        {  // It is a search(key) Operation
            key = Double.parseDouble(line.substring(i+1, k));
            val = null;
        } 
        else 
        { // Operation is either insert(key, val) or search(key1, key2)
            key = Double.parseDouble(line.substring(i+1, j));
            val = line.substring(j+1, k);
        }
        
        
        String msg = "";
        if (method.equalsIgnoreCase("Insert")) 
        {
            tree.insert(key, val);
            return null;
        }
        else if (method.equalsIgnoreCase("Search")) 
        {
            if (val == null) 
            { // Key searching
                ArrayList<String> values = tree.search(key);
                if (values.size() == 0)
                	msg = "Null";
                else 
                {
                    msg += values.get(0);
                    for (int a = 1; a < values.size(); a++) 
                    {
                        msg += "," + values.get(a);
                    }
                }
            } 
            else 
            { // Range searching
                
                ArrayList<KeyValPair> pairs = tree.search(key, Double.parseDouble(val));
                if (pairs.size() == 0) 
                	msg = "Null";
                else 
                {
                    msg += pairs.get(0);
                    for (int a = 1; a < pairs.size(); a++)
                    {
                        msg += "," + pairs.get(a);
                    }
                }
            }
        }
        else 
        	System.out.println("Wrong method!");

        return msg;

    }

    
    

}
