import java.util.l*;

public class ST <Key, Value>
{
    /*
    St stands for symbol table, a data structure which associate to a specific key, a value given by the client.
    So, it creates an element composed of a couple Key-Value. Its function is based on the adding of elements 
    (an insert method) and a search method
    */
    
    /*
    Another interesting implementation of a st is to use a linkedlist, so with a privat class node used
    to add elements
    */
    private int size;
    private Key[] keys;
    private Values[] values;
    
    public ST() //just the instance of the standard st. Here we create an empty st
    {
        size=0;   
        keys = (Key[]) new Object[size]; //initialization of the array of keys, with the cast at the item
        values = (Values[]) new Object[size]; //initializaton of the array of values, with the cast
    }
    
    public ST(int capacity) //another constructor for the st
    {
        size = capacity;
        keys = (Key[]) new Object[capacity];
        values = (Value[]) new Object[capacity];
    }
    
    public void insert(Key k, Value v)
    {
        delete(k); //if it's present a duplicate, we delete the corresponding value of the key passed in the method
        keys[size] = k;
        values[size] = v;
        size++;
    }
    
    public void delete(Key k)
    {
        for(int i=0; i<size; i++)
        //currently, we r just searchin throught all the array for the key k
        {
            if(keys[i] == k) //if found
            {
                keys[i] = keys[i-1];
                values[i] = values[i-1]; //this think is a little bit tricky. Based on the kind of strcutre we use to
                //implement our st, we put the i-1th element of the array, or the i+1th element
                values[i-1] = null;
                keys[i-1] = null;
                size--;
            }
        }
    }
    
    public Value get(Key k) //a method that returns a specific value given the specific key k
    // In this method we travel all along the entire st to find the value corresponding to the given key
    {
        for(int i=0; i < size; i++)
        {
            if(keys[i] == k)
            {
                return values[i];
            }
        }
    }
    
    public boolean Contains(Key k) //a method which is similar to k, but don't returns the value of the
    //given key, but rather the boolean if the key is in the st
    {
        for(int i=0; i < size; i++)
        {
            if(keys[i] == k)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isEmpty()
    {
        return size ==0;
    }
    
    //just standard methods 
    
    public int size()
    {
        return size;
    }
    
    public Iterator<Key> Keys()
    {
        return KeyIterator();
    }
    
    private class KeyIterator implements Iterable<Key>
    {
        Key[] copy;
        int sizeopy;
        
        public KeyIterator()
        {
            copy = (Key[]) new Object(keys.length);
            size = keys.length;
            
            for(int i=0; i < keys.length; i++)
            {
                copy[i] = keys[i];    
            }   
        }
        
        public boolean hasNext()
        {
            return sizecopy == size;
        }
        
        public Key Next()
        {
            if(hasNext())
            {
                sizecopy++;
                return copy[sizecopy];
            }else{
                throw new NoSuchElementException;
            }
        }
    }
    
}