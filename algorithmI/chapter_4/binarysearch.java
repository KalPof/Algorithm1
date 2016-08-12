public class binarysearch
{
    /*
    Binary search is a method used to search into an ordered array. With this kind of search, 
    the complexity of the algorithm is o(log n). The search is based on the logic of a binary tree where the 
    right child nodes have an higher value compared to the parental one, and the left node has a lower value.
    So the root has a value which is in the middle of the array (es 50 in number 1 to 99).
    The search looks at the left and right child node of the current node, if what we're searching is 
    bigger than the left one, we took the right node, and vice versa, recursively
    */
    
    static int k=0;
    public binarysearch()
    {}
    
    public static Comparable search(Comparable element, Comparable[] array, int first, int last) //the method used to sort
    {
        
        
        //if(element.compareTo(array[(last+first)/2]) == 0) return array[(last+first)/2];
        int mid = (last+first)/2; // the position in the array of the parental node of the last element
        
        //just a check if i've built an infinite loop. Niw it can have a stop
        System.out.println(array[mid]+ " cane");
        System.out.println(mid);
        //System.out.println(array.length);
        
        System.out.println(element);
        
        if(element.compareTo(array[mid]) == -1)
        {
            search(element, array, first, mid); // the recursive call if the element what we r searching is less than the mid one
        }else if(element.compareTo(array[mid]) == 1){
            search(element, array, mid, last);  // the recursive call if the element what we r searching is more than the mid one
        }else{
            k=mid;
            System.out.println("here");
            return array[k];
        }
        
        return array[k];
    }
    
    
    public static void main(String args[])
    {
        Integer[] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        binarysearch s = new binarysearch();
        Comparable e = s.search(array[19],array,0,array.length);
        System.out.println(e);
    }
    
    
    
}