public class stackofstrings<String> implements Iterable<String>
{
    private int size;
    private String[] inserter;
    
    //those are just the main components of the stack
    public stackofstrings(int capacity)
    {
        size = 0;
        inserter = new String[capacity];
    }
    
    public void push(String s)
    {
        insterter[size] = s;
        size++;
    }
    
    public String pop()
    {
        String k = insterter[size--];
        inserter[size] = null;
        return k;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public Iterator<String> Iterator(){ return new IteratorCopy()}
    
    public class IteratorCopy implements Iterator<String>
    {
        
         // a standard iterator method foundable in java.util.iterator has 3 main methods: hasnext, next and remove
        // the itherator method is used to enable our class to use foreach (object : Collection) cycles on the structure
        
        private int counter = 0;
        
        public boolean hasNext()
        {
            return counter == size;
        }
        public String Next()
        {
            if(hasNext())
            {
                counter++;
                return inserter[counter];
            }else{
                throw NoSuchElementException();
            }
        }
        
        public void remove() 
        {
            throw new UnsupportedOperationException();
        }

        }
    }
}