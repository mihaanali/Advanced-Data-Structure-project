package NodeElements;

import java.util.ArrayList;

import NodeElements.KeyValPair;


  //We use DataNode to store and search the (key,value) pairs
 
public class DataNode extends Node 
{

    ArrayList<KeyValPair> kvpairs;      // Key-values pairs
    public DataNode dLeft;       // Points to the nearest left DataNode
    public DataNode dRight;      // Points to the nearest right DataNode

    
    public DataNode(int m, KeyValPair pair) 
    {
        this.m = m;
        isDataNode = true;
        n = 1;
        kvpairs = new ArrayList<>();
        kvpairs.add(pair);
        
    }

    // A constructor to initialize kvpairs, dLeft and dRight from a list of pairs, left node and right node
    public DataNode(int m, ArrayList<KeyValPair> pairs, DataNode left, DataNode right) 
    {
        this.m = m;
        isDataNode = true;
        n = pairs.size();
        this.kvpairs = pairs;
        this.dLeft = left;
        this.dRight = right;
    }

    /*
    Binary search is used to find the index of pair where insert is to be done.
    
    Either the index where the old pair has equal key or just larger key is returned 
    or kvpairs.size() is returned if no such pair is found
    
    */
    private int searchKeyValPair(double key) 
    {
        int left = 0;
        int right = kvpairs.size() - 1;
        while (left <= right) {
            int mid = (left + right)/2;
            if (key == kvpairs.get(mid).getKey()) 
            {
                return mid;
            }
            else if (key < kvpairs.get(mid).getKey())
            {
                right = mid - 1;
            }
            else left = mid + 1;
        }
        return left;
    }

    /*
     The given key is used for matching with the pairs, and the values are added to the list
     
      Vals is the list which will have all values after search in the end
     */
    public void search(double key, ArrayList<String> vals)
    {
        int index = searchKeyValPair(key);

        // All values that match are added in the right
        DataNode node = this;
        int rt = index;
        while (true) 
        {
            if (rt >= node.kvpairs.size() && node.dRight != null) 
            {
                node = node.dRight;
                rt = 0;
            }
            if (rt < node.kvpairs.size() && key == node.kvpairs.get(rt).getKey())
            {
                vals.add(node.kvpairs.get(rt).getValue());
                rt++;
            }
            else break;
        }

        // All values that match are added in the left
        node = this;
        int lt = index - 1;
        while (true) {
            if (lt < 0 && node.dLeft != null) 
            {
                node = node.dLeft;
                lt = node.kvpairs.size() - 1;
            }
            if (lt >= 0 && key == node.kvpairs.get(lt).getKey())
            {
                vals.add(0, node.kvpairs.get(lt).getValue());
                lt--;
            }
            else break;
        }
    }

    /*
     The first index is obtained by using key2, All the pairs are then obtained by accessing the two links
     
     ls has the resulting list of pairs
     */
    public void search(double key1, double key2, ArrayList<KeyValPair> ls) 
    {
        int index = searchKeyValPair(key2);
        

        // All values that match are added in the right
        DataNode node = this;
        int rt = index;
        while (true) {
            if (rt >= node.kvpairs.size() && node.dRight != null)
            {
                node = node.dRight;
                rt = 0;
            }
            if (rt < node.kvpairs.size() && key1 <= node.kvpairs.get(rt).getKey()
                    && key2 >= node.kvpairs.get(rt).getKey()) {
                ls.add(node.kvpairs.get(rt));
                rt++;
            }
            else break;
        }

        // All values that match are added in the left
        node = this;
        int lt = index - 1;
        while (true) {
            if (lt < 0 && node.dLeft != null) 
            {
                node = node.dLeft;
                lt = node.kvpairs.size() - 1;
            }
            if (lt >= 0 && key1 <= node.kvpairs.get(lt).getKey()
                    && key2 >= node.kvpairs.get(lt).getKey())
            {
                ls.add(0, node.kvpairs.get(lt));
                lt--;
            }
            else break;
        }
    }

    // The order is maintained by using binary search for inserting a new pair
    public void insert(KeyValPair pair)
    {
        int index = searchKeyValPair(pair.getKey());
        kvpairs.add(index, pair);
        n++;
        
    }

    
     // Splitting an overfull data node
      
     
    public IndexNode split() 
    {

        // move the right half children to new data node
        ArrayList<KeyValPair> newPairs = new ArrayList<>();
        for (int i = kvpairs.size() - 1; i >= m/2; i--)
        {
            newPairs.add(0, kvpairs.get(i));
            kvpairs.remove(i);
        }

        // reset double linked list pointers
        DataNode left = this;
        DataNode right = this.dRight;
        DataNode newNode = new DataNode(m, newPairs, left, right);
        this.dRight = newNode;
        if (right != null) right.dLeft = newNode;

        // create a new index node to merge with parent
        n = kvpairs.size();
        
        return new IndexNode(m, newPairs.get(0).getKey(), newNode);

    }

    //For Overriding -> key1,key2...
    public String toString() 
    {
        String str = "";
        if (kvpairs.size() == 0) return str;
        str += kvpairs.get(0);
        for (int i = 1; i < kvpairs.size(); i++) {
            str += "," + kvpairs.get(i);
        }
        return str;
    }

}
