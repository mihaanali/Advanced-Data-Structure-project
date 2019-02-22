package NodeElements;

import java.util.ArrayList;

import NodeElements.KeyValPair;


  	//IndexNode does not have data pair and is only used for DataNode searching
 
public class IndexNode extends Node 
{

    private ArrayList<Double> keys;     // Taken for a list of keys, i will be left child, i+1 right for the ith key
    private ArrayList<Node> listchildren;   // Gives us a list of children

    // A Constructor to initialize from lists of keys and children 
    public IndexNode(int m, ArrayList<Double> keys, ArrayList<Node> children) 
    {
        this.m = m;
        n = keys.size();
        this.keys = keys;
        this.listchildren = children;
    }

    // A Constructor to initialize from a key and its child 
    public IndexNode(int m, double key, Node node)
    {
        this.m = m;
        n = 1;
        keys = new ArrayList<>();
        keys.add(key);
        listchildren = new ArrayList<>();
        listchildren.add(node);
    }

    // We are using this to get all the keys
    public ArrayList<Double> getKeys() 
    {
        return keys;
    }

    // We are using this to get all the children
    public ArrayList<Node> getChildren()
    {
        return listchildren;
    }

    
    // We are using a binary search here to find the index of child which may contain key
    
    private int searchKey(double key) 
    {
        int left = 0;
        int right = keys.size() - 1;
        while (left <= right) 
        {
            int mid = (left + right)/2;
            if (key == keys.get(mid)) 
            {
                return mid + 1;
            }
            else if (key < keys.get(mid))
            {
                right = mid - 1;
            }
            else left = mid + 1;
        }
        return left;
    }

    
      // We insert the given node to the first place of children list
    
     
    public void addToFirst(Node node) 
    {
        listchildren.add(0, node);
    }

    /*
      The function below searches for the corresponding child which should take this pair
      The address of corresponding child is returned by the function
     */
    public Node searchChild(KeyValPair pair) 
    {
        double key = pair.getKey();
        int index = searchKey(key);
        return listchildren.get(index);
    }

    /*
      	The function below searches for the child who may have the pair with given key
   		The address of corresponding child is returned by the function
     */
    public Node searchChild(double key)
    {
        int index = searchKey(key);
        return listchildren.get(index);
    }

    /*
      Splitting an overfull index node
      A new index node with the new index node as its child is returned by the function
     */
    public IndexNode split() 
    {

        // In this, the right half keys and children are moved to the new index node
        ArrayList<Double> newKeys = new ArrayList<>();
        ArrayList<Node> newChildren = new ArrayList<>();
        newChildren.add(listchildren.get(listchildren.size() - 1));
        listchildren.remove(listchildren.size() - 1);
        for (int i = keys.size() - 1; i > m/2; i--) 
        {
            newKeys.add(0, keys.get(i));
            newChildren.add(0, listchildren.get(i));
            keys.remove(i);
            listchildren.remove(i);
        }

        // We use a new index to store the new keys and children
        IndexNode newNode = new IndexNode(m, newKeys, newChildren);

        // We have created a new index node here to merge with the parent
        double key = keys.get(keys.size() - 1);
        keys.remove(keys.size() - 1);
        n = keys.size();
        
        return new IndexNode(m, key, newNode);

    }

    // The merge operation is performed with a newly created node which has only one key and one child
     
     
    public void mergeWith(IndexNode node) 
    {
        double key = node.getKeys().get(0);
        Node child = node.getChildren().get(0);
        int index = searchKey(key);
        keys.add(index, key);
        listchildren.add(index+1, child);
        n++;
        
    }

   
    // For Overriding -> key1,key2... 
    public String toString()
    {
        String str = "";
        if (keys.size() == 0) return str;
        str += keys.get(0);
        for (int i = 1; i < keys.size(); i++) 
        {
            str += "," + keys.get(i);
        }
        return str;
    }
}
