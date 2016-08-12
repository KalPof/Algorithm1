public class balancest<Key implements Comparable<Key>, Value>
{
    /*
    A balanced symbol table (a search tree, in this case) is a balanced type of st. An usual kind of balancst is a 2-3 st
    A 2-3 st is a search tree where the nodes present respectively 2 child nodes nodes and 1 key (the 2 one), and 3 child nodes and 2 keys (the 3 one)
    (a better explanation can be found here http://algs4.cs.princeton.edu/33balanced/).
    
    A balanced one is a st where all the links to the child nodes are null or all at the same distance from the root.
    The child nodes are indipendent from the parental ones, so a 2 node can give "birth" to a child 3 node. 
    The logic of a binary tree btw remains so a 3 node needs that the 2 keys are greater compared to the parental one, if the node is on the right, 
    and viceversa for the left.
    Be carefoul btw, this is true only for the rightmost element and the left most element, the elements which are not them needs a different logic.
    
    In fact the position of the node must be refered not only to the parental one, but sometimes to the node on top of the parental one. Better explanation is below
    The methods usually implemented are search, insert and delete. The insert and delete ones are really tricky, due to a different type of nodes (inserting into
    a 3 node is different between a 2 node, and inserting into a 2 node is different between the insertion into a single one), so i need to study them well
    */
    
    
    /*
    the implementation that we use is a redblack st (rbst). This kind of structure uses 2 booleans (red and black) to identify the kind of node (if it's a red one
    or a black one). If red is true, the node is a 3 node (2 keys), and is used as it. A red link is used to "merge" 2 nodes, forming a 3 node. The black link is
    instead the standard link between 2 nodes
    */
    
    private Node root;
    private static final boolean red = true; //used to reference to a 3 node ( 3 links + 2 keys in the same node)
    //this kind of structure is the "natural" implementation of a 3 node, so 2 nodes concatenated (as figured here 
    // http://algs4.cs.princeton.edu/33balanced/images/redblack-encoding.png or here http://algs4.cs.princeton.edu/33balanced/images/redblack-1-1.png)
    
    /*As you can see in the images, the child nodes are added in a specific manure, so the left is less than a-b, the middle is  in between and the right is more*/
    
    private static final boolean black = false; //just to clarify http://algs4.cs.princeton.edu/33balanced/images/redblack-color.png
    //dunno y black = false and red = true, hope that i'll be clarified below
    
    // the static final variable means that it's "unvariable" (cannote change)
    
    private class Node //the private class node used to
    {
        private Key k; 
        private Value v;
        private Node left, right; //the nodes presents on the left and the right of the current node
        private boolean color; //the color which reference to a 3 node or a 2 node
        
        public Node(Key k, Value v, boolean color)
        {
            this.k=k;
            this.v=v;
            this.color = color;
        }
    }
    
    public balancest() //standard constructor
    {
        root = null;
    }
    
    public Value search(Key k) // the public method which calls the private method
    {
        return search(root,k).value;
    }
    
    private Node search(Node n, Key k) // the private methods which really do the search
    {
        if(n == null) return null;
        
        int compare = k.compareTo(n.k);
        if(compare > 0) search (n.right,k); //recursive call. travel down the tree following the right branch
        else if(compare < 0) search (n.left, k); //recursive call. travel down the tree following the left branch
        else return n.val;
    }
    
    public void insert(Key k, Value v) //the insert method. We use this to insert new nodes into the balanced st. 
    //it's important to figure out when a node has a black link or a red link
    {
        root = insert(root,k,v); //call to the private method
        root.color = black; //i've (may be) figured it our now. the color variable of node is a call at the 2 boolean variable instantiated
        // on the top of the function
    }
    
    private Node insert(Node n, Key k, Value v)
    {
        if(n == null) return new Node(k,v,red);
        
        int compare = k.compareTo(n.k);
        if(compare > 0) insert(n.right,k,v);
        else if(compare < 0) insert(n.left,k,v);
        else n.v = v;
        /*
        I don't really get this one. If i've found the node n (which is obviously at this point already present in the 2-3 st) ok, nothing wrong
        If it's not present, how can i change the 2-3 st? am i adding only nodes to it without changing anything...
        May be the red link works as the same for the 3 node, which has the same value, and not a different one, but i don't think so...
        
        EDIT: kinda got it. Pratically we alternate the insert colors (first, a red link, then a black link, and so on). Then, when 2 nodes has the same key
        (dunno when, truly saying), we rotate the 2-3 st or we flips the colours. a better explanation can be seen with this photo 
        http://algs4.cs.princeton.edu/33balanced/images/redblack-construction.png        
        */
        
        if(isRed(n.right) && !isRed(n.left)) n = rotateLeft(n); //if the current node is merged with the right one (high value of node),
        //and the left node isn't, we use rotateLeft
        if(isRed(n.left) && !isRed(n.left.left)) n = rotateRight(n); //if the current node is merged with the left one (so the node has a low value
        //due to the combination of values of the left one and the current) and the node at the left
        if(isRed(n.left) && !isRed(n.right)) n = flipColors(n);
        
    }
    
    /*========= HELPER FUNCTIONS FOR INSERT ==============*/
    
    public isRed(Node n) //an helper function built to see if the node has a red link or not
    {
        if(n != null) return n.color == red;
        return null;
    }
    
    public Node rotateLeft(Node n)
    /*This method is used to exchange the position between 2 nodes. The method starts for a given node n, and from that node checks
    the right node (setting a new node equals to). In that case the right node is greater compare to the parental one. 
    The method tooks the right branch and it sets up has the top branch. The parental node becomes in fact the left node. In this case, the child nodes from the 
    child one (the one moved on top) are moved one the left branch (the old parental one), the one which is lower than the right child (now on top), and the 
    other becomes the new right branch. The old parental one becomes now a child node with the left child-child node (on the right), and the old left node (on the left)
    A better explanation is above and here: http://algs4.cs.princeton.edu/33balanced/images/redblack-left-rotate.png*/
    {
        Node newNode = n.right; //instance of a new node equals to the right of the node passed
        n.right = newNode.left; //moved the left child-child node on the right of the new child node
        newNode.left = n; //moving the old parental node on the left of the new parental node (the old right one)
        newNode.color = n.color;
        n.color = red;
        return newNode;
    }
    
    public Node rotateRight(Node n)
    /*This method is the same as the previous one, only "rotates" the tree to the right. The method in fact does the "inverse function" of rotateLeft.
    In fact, we move the parental node and the left one. The left node is now moved on the parental, and the parental node becomes the right node. The left-left node
    remains as it is (becomes the left node), the middle node becomes the left node of the new child node and the right node becomes the new right-right node.
    A better explanation here http://algs4.cs.princeton.edu/33balanced/images/redblack-right-rotate.png*/
    {
        Node newNode = n.left; //instance of a new node equals to the left child node of the passed node
        n.left = newNode.right; //we've now moved the right child-child node on the left of the new child node
        newNode.right = n; //we've moved the old parental node on the right of the old left-child node(the new parental one)
        newNode.color = n.color;
        n.color = red;
        return newNode;
    }
    
    public void flipColors(Node n)
    /*This method is used to resolve a "4 node", which is only a node with 2 nodes connected to the parental one which one with a red link (forming, in this case
    a 4 node, with 3 keys and 4 links). The resolution of this is only to exchange the colors of the links from red to black, forming, in this case 2 new child nodes
    from the starting 4 node. The child-child nodes remains unvariated, because they're already in position. 
    A better explanation http://algs4.cs.princeton.edu/33balanced/images/color-flip.png*/
    {
        if(n.right.color == red && n.left.color == red)
        {
            n.color = red; //create a new 3 node from the parental one with the "parental parental" one
            n.right.color = black;
            n.left.color = black; //creation of 2 new child nodes from the 4 node
        }
    }
    
    /*========= DELETION ? =========*/
    
    /*I really want to also build the deletion algorithm, but now i don't have so much time. May be after..*/
    
}