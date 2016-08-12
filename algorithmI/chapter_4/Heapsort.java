public class Heapsort
{
    /*this class is just use a binary heap kind of structure to develop a powerfull sorting named in fact heapsort
    Heapsort is one of the best method of sorting avaible. Is based on the creation of an heap kind of structure (a max heap) from an array
    and, with that we just sort the heap.
    */
    private Heapsort() {} //private instance of the constructor, may be i'm stupid but i don't get why i need this
    //this is probably needed as a constructor of the class heapsort
    
    public static void sort(Comparable[] pq) //here we sort the queue. I use pq as variable so it's more clear
    //i can obviously use array. The only thing needed is to be a Comparable[]
    {
        int size = pq.length;
        
        for(int i=(size/2); i >= 1; i--) 
        //this method sinks the array in ascending order (the one i've built were more std, sorting in descending order)
        {
            sink(pq,i,size); //sink moves the child node upper, due to the fact that the parental one has a lower key
            // the method is used to created an heap ordered array, perfect to sort after that
            //printer(pq);
        }
        
        //printer(pq);
        
        
        while(size > 1) //the other part used for the sorting
        {
            exchange(pq, 1, size); //here we exchange the last element of the array (size), which is the lowest one, with 
            //the one on top (the root)
            size--;
            sink(pq,1, size); //after decreasing the size, we sink our heap leaving the last element (which is the one exchanged before)
        }
        
        
        /* just a mine try of implementing an ascendi heapsort
        int p=0;
        while(p < size)
        {
            exchange(pq,p,size);
            p++;
            sink(pq,p,size);
        }
        */
        printer(pq);
        
    }
    
    private static void printer (Comparable[] array)
    {
        for(int v=0; v < array.length; v++)
        {
            System.out.println(array[v]);        }
    }
    
    private static void sink(Comparable[] pq, int k, int size)
    /*
    This method is used to change the position of 2 nodes, but in this case the parental one has a key lower than his child.
    In this case we need to move the parental one down the tree. In that case, if k is the parental one, the child one has 2k
    Here we use a sink with a priority queue mades of Comparable elements (the array), a pointers and the size
    */
    {
        while(2*k <= size) //we traved down the tree until k doesn't reach the last child node
        {
            int j = 2*k; //this is the position of the child node of the parent one, the other is (due to the binary kind of structure) 2k+1
            if(j < size && less(pq,j,j+1)) j++; // this if is to increment j if the right is bigger compared to the left one, so we 
            //enshure that the exchange occur with the greater node
            if(!less(pq,k,j)) break; // self explanatory
            
            exchange(pq, k, j);
            k=j; //we use the last 2 operations to travel down our heap
        }
    }
    
    private static void exchange(Comparable[] pq, int i, int j) // just the standard exchanger foundable in the other methods
    // btw we need to diminuish the pointers (-1) because this implementation use a different kind of order (is descending)
    // and we 
    {
        
        Comparable swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
        
    }
    
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static void main(String args[])
    {
        Heapsort j = new Heapsort();
        Integer[] intar = {1,4,3,5,8,6,9,4,2,3};
        j.sort(intar);
        j.printer(intar);
    }
}