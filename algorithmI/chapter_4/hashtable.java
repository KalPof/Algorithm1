public class hashtable<Key implements Comparable<key>, Value>
{
    /*
    An hash table is a specfic kind of structure which creates a relation between a key and a value. The concept is not too far from the logic of a st, but
    here we've a different kind of logical thinking. In hash tables the values stored into the array are referenced with keys that are transformed into an array
    index with a specific hash function. Given a key, the hash function transforms it into THE SPECIFIC index of the element into the array, referencing at it, and at it 
    only. For the creation of a specific index for the specific element into the array, we usually use part of the element (es if the key is a phone number, part of the number
    or the entire number also) to reference the element. 
    
    The hash function transforms the key into an integer from 0 to array.length-1, so every element in the array can be (theoretically, at least) referenced to a specfic 
    key. In fact, collisions can happend. Collisions are where the injective function "fails" (means that the entire hash table is no more injective, 2 keys points to the same
    element in the array). The creation of an higher number of key-element is also a problem, due to an increase of the load factor.
    
    Several methods are used to resolve collisions: 1) the use of the next free cell, travelling throught all the array until we find the first of them 2) quadratic index
    (using a quadratic index until we find a free cell) 3) 2 hash functions, where a collisional key is used into another hash function to create a new table 4) creation
    inside of the cell, of an array which contains multiple values
    
    */
    
    /*For this data structure, i use more than 1 class, i'll use some classes just to see all the different implementations*/
    
    public class PhoneNumber
    {
        private int area; //the area code of of a std phone american number
        private int exchange; // the middle 3 digits
        private int extension; //the last 4 digits
        
        public phone(int i1, int i2, int i3) //std constructore 
        {
            area = i1;
            exchange = i2;
            extension = i3;
        }
        
        public getarea(){return this.area;}
        public getexchange(){return this.exchange;}
        public getextension(){ return this.extension; }
        
        @Override
        public String toString()
        {
            return String.format("the phone is " + area + "-" + exchange + "-" + extension);
        }
        
        @Override 
        public boolean equals(Object obj)
        {
            if(obj != null)
            {
                if(obj = this) return true;
                if(obj.getClass() != this.getClass()) return false;
                PhoneNumber obj = (PhoneNumber) obj; //create a new PhoneNumber object to use ==
                return (this.area == obj.area && this.exchange == obj.exchange && this.extension == obj.extention);
            }
            return false;
        }
        
        @Override
        public int hashCode()
        /*This hash method overrides the standard hashCode method. The hash function creates, based on the values
        of area, exchange and extention, a new hash value which is higly specific for the phone number*/
        {
            return 31 * (area + 31 * exchange) + extension; //31 is a specific hash number for java, casue it's prime and have some other mathematical features
        }
        
        
    }
    
    public class Transaction implemennts Comparable<Transaction>
    {
        private String who;
        private Date when;
        private double amount;
        
        public Transaction(String who, Date when, double amount)
        {
            this.who = who;
            this.when = when;
            this.amount = amount;
        }
        
        //some getters
        
        public String  g_who(){ return this.who;}
        public Date g_when(){return this.when;} 
        public double g_amount(){return this.amount;}
        
        @Override
        public String toString()
        {
            return String.format("the transaction is " + amount + "$, made by " + who + " in date "+when);
        }
        
        @Override
        public boolean equals(Object obj)
        {
            if(obj != null)
            {
                if(obj = this) return true;
                if(obj.getClass() != this.getClass()) return false;
                transaction obj = (Transaction) obj; //create a new Transaction object to use ==
                return (this.who == obj.who && this.when == obj.when && this.amount == obj.amount);
            }
            return false;
        }
        
        public int hashCode()
        /*This method hashcode is different from the previous one. Starts from an int hash = 1, and then increments it. As usual, the int returned at the end 
        it's based on the specific value of the element, to prevent the formation on collision*/
        {
            int hash = 1;
            hash = hash*31+who.hashCode();
            hash = hash*31 + when.hashCode();
            hash = hash*31 + ((Double) amount).hashCode();
            return hash;
        }
        
        public static class WhoOrder implements Comparator<Transaction> //compare of the values of the function 
        {
            @Override
            public int compare(Transaction v, Transaction w) 
            {
                return v.who.compareTo(w.who);
            }
        }
        
        public static class WhenOrder implements Comparator<Transaction> //compare of the values of the function 
        {
            @Override
            public int compare(Transaction v, Transaction w) 
            {
                return v.when.compareTo(w.when);
            }
        }
        
        public static class AmountOrder implements Comparator<Transaction> //compare of the values of the function 
        {
            @Override
            public int compare(Transaction v, Transaction w) 
            {
                return v.amount.compareTo(w.amount);
            }
        }
        
    }
    
    public class LinkedHashTable<Key, Value>
    {
        /*
        This kind of structure if just a symbol table implemented using the same logic as an hash table. This can be done in 2 ways, thorught an array
        of SequentialSearchST (which is a java structure which creates a symbol table as a linked list, in fact, sequential) with key,value pair or throught nodes 
        composed of a key, a value and a pointer to the next node. The array and the nodes are usefull to get into
        the logic of the resolution of collisions using a "second" hash table, who maps the collision to another hash, return so the 2 different values.
        i'll build the node one, which is more straightforwardf imo
        */
        
        private int m; //stands for the number of key-value pairs presents into the Hash table
        private Node[] nodearray; // an array of nodes, used to create the linked list
        
        private class Node
        {
            Object key;
            Object value; // we create a node composed of a Key value pair (usefull for the hashtable implementation, and a link to the next node)
            Node next;
            
            public Node(Object k, Object v, Node n)
            {
                key = k;
                value = v;
                next = n;
            }
        }
        
        public LinkedHashTable() //constructor of a standard hash table. Hash table in this case of size 100
        {
            this(100); //don't really get this kind of write, but k
        }
        
        public LinkedHashTable(int size) // the real constructor
        {
            m = size;
            nodearray = new Node[size];
        }
        
        private int hash(Key key) {
            return (key.hashCode() & 0x7fffffff) % m; //this method is a call of an hash function given a key
            //the key calls the hashCode function presents in every object, and the int returned is this value divided by the number of key value pair (m)
            // dunno the hexadecimal
        } 
        
        public Value search (Key k) //a public method used to search for a key k.
        {
            int i = hash(k); //the integer is the hash code specific for the given key in the method
            
            for(Node n = nodearray[i]; n != null; n = n.next)
            /*This method is really interesting. We put the node n equals to the node with the hash code for the given k. From that
            we travel throught all the array and end only at n = null. The increment is the next element (as an usual linked list)*/
            {
                if(k.equals(n.key)) return (Value) n.value; //if the key is equals, return the value corresponding to it
            }
            
            return null;
        }
        
        public Value insert(Key k, Value v)
        {
            int val = hash(k); //just the int returned from the hash function of the given k
            
            for(Node n = nodearray[val]; n != null; n = n.next) // as before
            {
                if(k.equals(n.key){ n.value = v; return; }// as before
            }
            nodearray[val] = new Node(k,v,nodearray[val]); //adding a new node. 
            //If it's already present a node in the same hash, we only add this new node to his next
        }
        
        public Value delete()
        {}
        
        public Iterable<Key> keys()  //returns an iterator for all the keys in the array
        {
            Queue<key> queue = new Queue<Key>(); //instance of the iterator (in this case a queue)
            for(int i=0; i < m; i++)
            {
                for(Node n=nodearray[i]; n != null; n= n.next)
                {
                    queue.enqueue((Key) n.key);
                }
            }
            return queue;
        }
        
        
    }
    
    
    
    
}