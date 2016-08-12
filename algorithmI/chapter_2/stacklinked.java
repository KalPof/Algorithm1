public class stacklinked<Item> implements Iterable<Item>
{
    private class Node<Item>
    {
        Item item;
        Node<Item> next;
    }
    
    public Node<Item> Element;
    int size;
    
    public stacklinked()
    {
        size =0;
        Element = null;
        
    }
    
    public void push(Item obj)
    {
        Node<Item> prev = Element; //we create a new node equals the previous one, so now we can connect them
        Element = new Node<Item>; //we instantiate the new node for the connection of the 2
        Element.item = obj; 
        Element.next = prev;
        size++; //just a ++ of the size
    }
    
    public Item pop()
    {
        //as a stack, the pop method kicks out the last element of the stack
        
        Item item = Element.item;
        Element = Element.next;
        size--;
        return item;
    }
    
    public Iterator<Item> Iterator(){ NodeIterator(); }
    
    public class NodeIterator implements Iterator<Item>
    {
        Node<Item> n;
        
        public NodeIterator()
        {
            n = Element;
        }
        
        public Node next()
        {
            if(Element.next != null)
            {
                n = n.next; //we use this to prevent the growth of Element (the our initial node)
            }
            
            return n; // i return the entire node, and not only the item
        }
        
        public boolean hasNext()
        {
            return Element.next != null;
        }
        
        public void remove(Node node)
        {
            /*
            while(node != n)
            {
               n=next();
               if(n.next == null){ return NoSuchElementException(); }
            }
            the previous code is made by me, but the book use a different kind of remove, dunno y. Solved: cause remove is optional and usually 
            bugs the code
            */
            
            throw new UnsupportedOperationException();
        }
    }
}