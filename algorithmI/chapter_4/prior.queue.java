public class prior_queue<Key extends Comparable<Key>>
{
    /*
    Priority queues are normal queues where elements stored inside the data structure are "linked" with some values, which we can use 
    to reference them and delete them in a specifically order. Keeping track of the elements in thhe queue is so usefull to delete the maxmimum
    element added into the queue, and only this.
    That said, i don't really get the Key "thing" passed in the class. Key is somehow the element that we use to relate the value to the element 
    himself added (aka a couple key-value, as a python dictionary, which is, if i dont get wrong, a priority queue, or something related).
    */
    private Key[] pq; // the declaration of the array of keys which we instanciat into the constructor
    private int size; //the size of the array
    
    public prior_queue(){} //just an empty constructor 
    
    public prior_queue(int capacity) //just a standard constructor for a queue
    {
        pq = (Key]) new Comparable[capacity];
    } 
    
    public static void insert(Key k) //the method used to insert the keys into our priority queue
    {
        size++;
        pq[size] = k;
    }
    
    public boolean isEmpty() // a standard isEmpty for queues
    {return size == 0;}
    
    
    public static Key delMaxKey() //this method returns the smallest key in the queue
    {
        int max=0;
        for(int i=0; i<= size; i++)
        {
            if(less(pq[max], pq[i])){ max = i; }
        }
        
        exchange(pq, max, size-1);
        size--;
        return pq[size];
    }
    
    
    /* the methods below are used in delMaxKey to find the smallest key in the array */
    
     public static void exchange(Comparable[] a, int i, int j)
    {
        Comparable parking = a[i];
        a[i] = a[j];
        a[j] = parking;
    }
    
    public static boolean less(Comparable a, Comparable b)
    {
        if(a.compareTo(b) == 1){ 
            return false;
        }else{
            return true;
        }
    }
    
    /*
    Per capirci, l'utilizzo di una priority queue è tipo questo: creiamo una priority queue di un elemento specifico. (es una data) 
    La nostra data verrà poi letta da input (un esempio comodo sono letture da file), creando così prima una stringa (es) e poi convertita
    nel nostro tipo di oggetto desiderato tramite l'instanziazione di un nuovo oggetto di quella classe. Da ciò possiamo così aggiungerla 
    all'interno della nostra queue, e processare così la queue
    */
}