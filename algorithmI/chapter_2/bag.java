// that class is used for an implementation of a bag like kind of structure

public class bag<Item> implements Iterable<Item> // on the bag, we use items that are iterable, which is usefull for the check
{
    
    private int size;
    private Iterable<Item> array ; // i don't remember well the cast for item 
    
    // a private method ResizeArray would be usefull for the resize of the array
    
    public bag(int capacity) // the constructor is an empty structure we call a bag, and the logic of the bag itself is done by methods
    {
        size=0;
        array = (Item)new Iterable[capacity]// we initialize the size of the bag to keep track of the bag size
    }
    
    public void add(Item item) //in this method we put on the bag some item
    {
        array[size] = item;
        size++;
    }
    
    public void remove(Item item)
    {
        for(int i=0; i < array.length() ; i++)
        {
            if(array[i] == item){array[i] = null;}
        }
        
    }
    
    public boolean isEmpty() // just the check for the empty bag
    {
        return size==0;
    }
    
    public int size()
    {
        return size;
    }
    
    public Iterator<Item> iterator()
    {
        return bagIterator();
        
        //this method is the one that brings me more problems, due to my lack of knowledge of java and the relatives basics functions as 
        //iterator, so pls sorry, my bad
    }
    
    public bagIterator implements Iterator(Item)
    {
        // a standard iterator method foundable in java.util.iterator has 3 main methods: hasnext, next and remove
        // gthe itherator moethod is used to enable our class to use foreach (object : Collection) cycle on the structure
        private int size_2 = 0;
        
        public boolean hasNext()
        {
            return size_2 == size;
            //just the check
        }
        
        public Item next()
        {
            if(hasNext())
            {
                size_2++;
                return array[size_2];
            }else{
                throw new NoSuchElementException();
            }
        }
        
        public void remove(Item item){
            throw new UnsupportedOperationException();
        }
    }
}