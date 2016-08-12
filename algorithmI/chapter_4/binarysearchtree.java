public class binarysearchtree<Key implements Comparable<Key>, Value>
{
    /*
    This is the implemenentation of a binary search tree using nodes. A binary search tree is a tree with nodes 
    (the private class inside of it). Each node on the left has a lower value compared to the parental one, and each on the right
    has a greater value compared to the parental (and to the one on the left). This is true recursively, so this is true for every node
    on the left and on the right a specific node n. The implementation is based on a symbol table
    */
    private node root;
    private int size;
    
    private class Node //the private class node
    {
        Key key; 
        Value value; //key and value of the node
        Node right; //the "pointer" of the node on the right (greater)
        Node left; // the "pointer" of the node on the left (lower)
        //int size; //the book also initialize a size, not really sure why
        
        public Node(Key key, Value value) //just a standard constructor, nothing strange here
        {
            this.key = key;
            this.value = value;
        }
    }
    
    public binarysearchtree() // a constructor for an empty bst (binary search tree)
    {
        root=null; //this is the instance of the root node
    }
    
    public Value search(Key k) //just the public method that calls the private one
    {
        return search(root,k);
    }
    
    private Value search(Node n, Key k)
    {
        if(n != null)
        {
            int cmp = k.compareTo(n.key); //cmp stands for compare. This is just the return of a compare between 2 keys
            if(cmp > 0) return search(n.right, k); /* the method calls recursively the function to travel down all the entire tree.
            if the compare found is 1, we move the current node to the child node on the right, and so on, until we find (recursively)
            a node with the same k (thanks to comparable) or a null node, and that's thre return state*/
            else if(cmp < 0) return search(n.left, k);
            else return n.value;
        }else{
            return null; //omg y a return null 
        }
    }
    
    public void insert(Key k,Value v)
    {
        if(k == null) throw new NullPointerException; //if the key is null, we cannot position the element into the bst
        if(v == null) remove (k) return; //if the key is present, but the value isn't, we must substitute the previous value with the current one (null)
        //same as deleting the key. The return is to prevent the insertion of a new element
        root = insert(root, k, v); //here's the call of a private method insert, which updates the root node, 
        //adding a new node. I don't get the return type node, but it's ok
    }
    
    private Node insert(Node n, Key k, Value v)
    {
        if(n == null) return new Node(k, v); //returns the instance of a new node n, with key k, value v
        int cmp = k.compareTo(n.key); //being keys comparable, we can compare them with a std compareTo method
        if(cmp < 0) n.left = insert(n.left, k,v); /*here the method begins to being tricky. If the compare is -1 (so k is lower than the one
        passed into the method), we recursively add a new node to the left of the passed node (the instance n == null) with key k and value v
        The method travels all along the entire bst until it find a null node, and in that position creates a new node n with the given parameters*/
        else if(cmp > 0) n.right = insert(n.right, k,v); //same as before, but with the right one
        return n; //returns the node inserted
    }
    
    public void delete(Key k)
    {
        if(key != null) root = delete(root,k);
    }
    
    private Node delete(Node n, Key k)
    {
        if(node == null) return null;
        
        int cmp = k.compareTo(n.key);
        if(cmp > 0) return delete(n.right, k);
        else if(cmp < 0) return delete(n.left, k); //the code here is the same as the search one (probably could be replaced)
        else //here's the real delete part
        {
            if(n.right == null) return n.left; //not really sure about this one
            if(n.left == null) return n.right; //ditto
            
            Node newnode = n; //take a copy of the node passed
            n = min(newnode.right);
            n.right = deleteMin(newnode.right);
            n.left = newnode.left;
            
            //i don't get the delete
        }
        
        return n;
    }
    
    public Key min() // a method used to find the minimun key into the bst. Is usefull for the implementation of delete and the Iterator of keys
    {
        return min(root).key; //this is the call of the private method min
    }
    
    private Node min(Node n)
    {
        if(n.left != null) min(n.left);
        return n;
    }
    
    public Key max() //as the previous one, this method return the key of the greatest element in the symbol table
    {
        return max(root).key; // call at the private function
    }
    
    private Node max(Node n)
    {
        if(n.right != null) max(n.right);
        return n;
    }
    
    public void deleteMax() //the method used to delete the maximum node into the st (+ call to the private method)
    {
        root = deleteMax(root); 
        if (root == null) throw new NoSuchElementException;
    }
    
    private Node deleteMax(Node n)
    {
        if(n.right == null) return n.left;
        else deleteMax(n.right);
        return n;
    }
    
    public void deleteMin() //the method used to delete the minimum into the st (+ call to the private method)
    {
        root = deleteMin(root);
        if(root == null) throw new NoSuchElementException;
    }
    
    private node deleteMin(Node n) // as above (don't get the delete part)
    {
        if(n.left == null) return n.right;
        else deleteMin(n.left);
        return n;
    }
    
    public Iterable<Key> keys() //this is the instance of the iterator built in the bst. With this method, we can travel throught all the keys.
    //we can use a for(Element e: Collection) with an iterator
    {
        return keys(min(), max());
    }
    
    private Iterable<Key> keys(Key k1, Key k2) // k1 and k2 stands for the minimum node in the bst and the maximum one, respectively
    {
        if(k1 == null || k2 == null) throw new NoSuchElementException;
        
        Queue<Key> q = new Queue<Key>; //here we use a data structure already built by java
        // i don't kinda like it, cause i really like to built myself the structures, but it's ok i guess
        //the method is a standard queue, which extends iterable, so we can travel the structure with no problems
        keys(root, q, k1, k2)
        return q; //we can return q cause it's iterable
    }
    
    private void keys(Node n, Queue<Key> q, Key k1, Key k2)
    {
        if(n == null) return; //stops here if null
        int compareLow = k1.compareTo(n.key); //the value of the methods compareTo between k1(minimun value) and the node passed, and between k2(max) and the node
        int compareHigh = k2.compareTo(n.key);
        
        if(compareLow < 0) keys(n.left, q,k1,k2);
        if(compareHigh > 0) leys(n.right, q, k1,k2);
        if(compareLow <= 0 && compareHigh >= 0) q.enqueue(n.key); //node found, we enqueue the key.
        //i don't know the queue methods, so i'll trust the method found on the site
    }
    
    /*
    IMPORTANT: In this class (the one which can be found here http://algs4.cs.princeton.edu/32bst/BST.java.html) there are several methods i've
    haven't implemented due to lack of time, laziness and a little bit of uselesness of the code (some methods are not fundamentals for the working of the class
    but are really usefull for the well buiding of the entire data structure, which i don't need, i need only to get into the data structure itself)
    */
    
    
}