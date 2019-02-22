package NodeElements;

//For storing data we define Key-Value pair here
 
public class KeyValPair implements Comparable<KeyValPair> 
{

    private final double key;
    private final String value;

    // A constructor for initialization
    public KeyValPair(double key, String data) 
    {
        this.key = key;
        this.value = data;
    }

    // The method returns the key of this pair
    public double getKey() 
    {
        return key;
    }

    // The method returns the value of this pair
    public String getValue() 
    {
        return value;
    }

    // We use a comparable interface here for our KeyValuePair
    public int compareTo(KeyValPair that) 
    {
        if (this.key < that.key) return -1;
        else if (that.key < this.key) return 1;
        else return 0;
    }

    @Override
    // The Function returns (key,value) pair representation
    public String toString() 
    {
        return "(" + key + "," + value + ")";
    }

}
