import NodeElements.*;

import java.util.ArrayList;
import java.util.LinkedList;


	//Here we make a B+ tree structure that can insert (key,value) pairs , search (key) and list pairs within a range search(key1,key2)
	 
public class BPlusTree 
{

		private Node root;  // root that can be IndexNode or DataNode
		private int m;      // order of B+ tree

		// A constructor for initializing the new B+ tree with order m 
		public BPlusTree(int m)
		{
			this.m = m;
		}

		//Here, we insert a new key-value pair into the tree
		 
		public void insert(double key, String val) 
		{
			KeyValPair kvpair = new KeyValPair(key, val);
			
			// Create a new DataNode as root if there is no data currently
			if (root == null) 
				root = new DataNode(m, kvpair);
			
			// otherwise insertion is done from root to DataNode in a recursive manner
			else insert(root, kvpair);
			
			if (root.isOverfull()) 
			{ 
				// new root created if it overfull
				
				IndexNode temp = root.split();
				temp.addToFirst(root);
				root = temp;
				
			}
        
		}

		// A Pair is inserted into this subtree recursively
		private void insert(Node node, KeyValPair kvpair)
		{

			// Insert a pair to the current node if it is a data node
			if (node.isDataNode()==true) 
			{
				node.insert(kvpair);
			}

			// Search for next node if the current node is an index node
			else 
			{
				Node nextNode = node.searchChild(kvpair);
				insert(nextNode, kvpair);
				// Split next node and merge current node with new entry if the next node becomes overfull
				if (nextNode.isOverfull()==true) 
				{
					IndexNode newNode = nextNode.split();
					node.mergeWith(newNode);
				}
			}

		}
    
     
      //Search for all values corresponding to given key
     
     
		public ArrayList<String> search(double key) 
		{

			ArrayList<String> vals = new ArrayList<>();
			if (root == null) 
				return vals;    // Return nothing as tree is void

			// Go all the way down along the path to get to the data node
			Node node = root;
			while (node.isDataNode()==false) 
			{
				node = node.searchChild(key);
			}

			// Add matching values from data node and its left neighbor
			node.search(key, vals);
			return vals;

		}

    
      //Searching for all the pairs whose key are between key1 and key2
     
     
		public ArrayList<KeyValPair> search(double key1, double key2) 
		{

			ArrayList<KeyValPair> pairs = new ArrayList<>();
			if (root == null) return pairs;

			// Use key2 to find one data node
			Node node = root;
			while (node.isDataNode()==false) 
			{
				node = node.searchChild(key2);
			}

			// Doubly linked list is used to add all pairs within the range
			node.search(key1, key2, pairs);
			return pairs; //returns a list of pairs that lie between key 1 and key 2, both inclusive

		}
}
