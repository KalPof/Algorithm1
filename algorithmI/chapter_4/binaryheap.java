import java.util.*;

public class binaryheap<Key> implements Iterable<Key>
{
    /*
    A binary heap is a specifica type of binary tree. Is in fact complete (means balanced from left to right, aka the same number of nodes 
    between the 2 branches). A complete binary tree has, for every node (means N nodes into the binary tree) an height of logN.
    A binary heap is just a priority queue based on the structure of a binary tree.
    
    A binay tree can be heap sorted (means that all the child nodes have a lower key compared to the parental one). As a array rerpresentive
    array[1] is the largest key in the structure
    */
    
    private Key[] pq; //just the declaration of our priority queue, which is in this case implemented as a binary heap
    private int size;
    private Comparator compare; //this comparator is optional, and i don't really get why i need it, may be to compare 2 keys with a different kind of compare
    /**Edit, needs for the compare2 method in less*/
    
    public binaryheap(int Capacity, Comparator<Key> c) //this constructor creates an empty pq
    {
        size = 0;
        this.compare = c;
        pq = (Key[]) new Object[Capacity+1]; // we use the +1 so the 1th element is actually the first element, position 0 is null
        
    }
    
    public binaryheap(Key[] array)
    {
        size = array.length;
        
        pq= (Key[]) new Object[array.length+1]; //instance of pq 
        
        for(int i=1; i<=array.length; i++) //just the copy of the array passed into pq
        {
            pq[i] = array[i--];
        }
        
        for(int k=size/2; k >= 1; k--) // using sink we create a heap ordered array ( any other key in the pq)
        {
            sink(k);
        }
        
        assert isMaxHeap(); // the check if the elements in pq are really heap ordered;
    }
    
    public void insert(Key v) //the method that we use to insert elements into the binary heap
    {
        size++;
        pq[size] = v;
        Swim(size); //we use to the check if the inserted key is sorted
        assert isMaxHeap();
    }
    
    public Key delMax() //return and deletes the max key present into the heap
    {
        Key max = pq[1];
        exchange(1, size--); //this time i don't really get why the --. after finding the max element
        // we need to exchange it with the last element in the heap
        sink(1); // sink is then used to resort a binary heap. After the deletion of the max element (the root one)
        //we have put the last element of top of the binary heap, and now the sink method, exchanging the keys until we create an heap sorted tree
        pq[size+1] = null; //just to help the garbage collector
        assert isMaxHeap();
        return max;
    }
    
    /*======= ITERATOR ======*/
    
    public Iterator<Key> iterator()
    {
        return new HeapIterator();
    }
    
    private class HeapIterator implements Iterator<Key> //definint the iterator used in this method to travel throught all the keys 
    {
        private binaryheap<Key> copy; //we create a copy of pq created in this class (creating in this case a new priority queue). This is just a declaration
        
        public HeapIterator()
        {
            copy = new binaryheap(pq.length, compare); //here we instanciate a new binary heap;
            for(int i=1; i<=size; i++)
            {
                copy.insert(pq[i]); //here we pass all the keys stored into pq and simply pass into copy
            }
        }
        
        public boolean hasNext()
        {
            return !copy.isEmpty(); //sorry but this time i don't get it
        }
        
        public Key next()
        {
            if(hasNext()) return copy.delMax();
            else  throw new NoSuchElementException(); //as before i don't get it
        }
        
        public void remove()
        { throw new UnsupportedOperationException();  }

    }
    /*======= HELPER FUNCTIONS =======*/
    
    private void Swim(int k) // k means the position of the node in the heap, and the parent one is in k/2
    /* this method is used to deal with insertion errors of nodes into the heap
    in particoular this method fixs if there are nodes with keys larger that it's parental one exchanging it
    with the one above. If the key is still greater that the parental above it, we only keep doing exchanges
    until we reach a normal heap sorted tree. here's a bettere explanation http://algs4.cs.princeton.edu/24pq/images/swim.png
    */
    {
        while(k > 1 && !less(k, k/2)) // this if is used to check if the child node has a greater key compared to the parental one (aka pq[k] > pq[k/2])
        {
           exchange(k, k/2);
           k = k/2; // this operation allows to walk throught the binary heap, setting k equals to the parental one, as described above
        }
    }
    
    private void sink(int k)
    /*
    This method is used to change the position of 2 nodes, but in this case the parental one has a key lower than his child.
    In specific, the different between sink and swim is that sink fix the a parental node has a lower key compared to the 
    2 childs below, so we need to move the current node down the tree, as here http://algs4.cs.princeton.edu/24pq/images/sink.png
    If k is the parental one, the child one has 2k 
    */
    {
        while(2*k <= size) //we traved down the tree until k doesn't reach the last child node
        {
            int j = 2*k; //this is the position of the child node of the parent one, the other is (due to the binary kind of structure) 2k+1
            if(j <= size && less(j, j+1)) j++; // this if is to increment j if the right is bigger compared to the left one, so we 
            //enshure that the exchange occur with the greater node
            if(!less(k, j)) break; // self explanatory
            
            exchange(k, j);
            k=j; //we use the last 2 operations to travel down our heap
        }
    }
    
    public void exchange(int i, int j)
    {
        Key parking = pq[i];
        pq[i] = pq[j];
        pq[j] = parking;
    }
    
    public boolean less(int a, int b)//i've changed the standard function beacuse before i needed a Comparable[]
    //(which obviously return comparable), now i'ìve got Keys and not comparables, so the method is changed
    {
        if(compare.compare(pq[a],pq[b]) == 1){ 
            return false;
        }else{
            return true;
        }
    }
    
    private boolean isEmpty() //just a function
    {
        return size==0;
    }
    
    // is pq[1..N] a max heap?
    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    // is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMaxHeap(int k) {
        /*This method in fact checks if a specific subtree (from k to array.length-1) is really heap sorted
        The check is done recursively for every child foundable in the tree from the starting point (meaning for every left and right branch)
        */
        if (k > size) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= size && less(k,left))  return false;
        if (right <= size && less(k,right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }

}
