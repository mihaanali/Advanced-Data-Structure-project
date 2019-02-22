package NodeElements;

import java.util.ArrayList;

import NodeElements.KeyValPair;

// We have defined this Abstract class Node for IndexNode and DataNode
public abstract class Node 
{

    protected int m;                // This being the node's order
    protected boolean isDataNode;   // To check whether it is a data node
    protected int n;                // This is for current node size, i.e. number of keys/pairs

    // A void constructor 
    public Node() 
    {

    }

    // An abstract search method that uses pair
    public Node searchChild(KeyValPair pair) 
    {
        return null;
    }

    // An abstract search method that uses key
    public Node searchChild(double key) 
    {
        return null;
    }

    // An abstract method for search
    public void search(double key, ArrayList<String> vals) 
    {

    }

    // An abstract method to search for range
    public void search(double key1, double key2, ArrayList<KeyValPair> ls) 
    {

    }

    // An abstract method for insertion
    public void insert(KeyValPair pair) 
    {

    }

    // An abstract method for splitting
    public IndexNode split() 
    {
        return null;
    }

    // An abstract mergeWith method
    public void mergeWith(IndexNode node) 
    {

    }

    // A method that returns if this node is a data node or not
    public boolean isDataNode() 
    {
        return isDataNode;
    }

    // A method that returns if this node is overfull or not
    public boolean isOverfull()
    {
        return this.n >= m;
    }

}
