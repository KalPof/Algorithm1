public class quick_sort
{
    /* quicksort is one of the best sorting alogirthm out there. Is based on the logic of mergesort (so recursion and mergin), but this time the partition comes first
    and the sorting is the last method applied. The plan is to shuffle randomly the array, partition it based on an entry k (the element that we take care of in fact is 
    the a[k] element, usually j=0, so we took the first element of the array) to which we can move the element, so every element left to it is less and every 
    element on his right is greater. After that, we just need to sort every portion of the array, (the one on the left and the other one on the right) and we're done
    The logic behind the sort is to watch a[k], took 2 pointers (es i and j) and increments/decrements i and k based on the value of a[i] and a[k]
    compared to a[k]. If a[i] > a[k] and a a[j] < a[k],, we just need to swap a[i] and a[j]
    */
    
    public quick_sort()
    {}
    
    public static void Shuffle(Comparable[] array)
    {}
    
    
    // this is i built the Partition code, not sure if right
    
    /*
    public static void Partition(Comparable[] array, int i, int j, int k)
    {
        while(i <= j && j >= i)
        {
            if(less(array[i],array[k])) i++;
            else if(!less(array[j],array[k])) j--;
            else exchange(array,i,j);
        }
        
        exchange(array,k,j);
    }
    */
    
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
    
    //this is whow the course build the code, surely better than mine. This is true mostly because my code doesn't check the precence of duplicate keys
    private static int Partition(Comparable[] array, int low, int high)
    {
        int i= low; int j= high +1; // i don't really get why high+1, probably due to the "instant speed" (mtg ndr) j--
        
        while(true) // we have breaks to deal with this
        {
            while(less(array[i++], a[low])){ if(i == high) break;}
            while(!less(array[j--], a[low])){ if(j == low) break;}
            
            if (i >= j) break;
            
            exchange(array, i, j);
        }
        
        exchange(array, low, j);
        
        return j; // the return is usefull to return the pointer of the
        
        //the code is somewhat similar to the one i've built before, dis i think is better (duno y...), so let's use this
    }
    
    public static void Sort(Comparable[] array)
    {
        
        // the call of the private methods foundable in this class
        Shuffle(array);
        sort(Array, 0, Array.length-1);
    }
    
    private static void sort(Comparable[] array, int low, int high)
    {
        // the real method that we use for the sorting of the array
        
        //as for mergesort, we use a recursive sort method to sort the 2 partitioned parts of the array.
        //Here, we need first to create
        if(high < low) return;
        
        int j = Partition(array, low, high);
        sort(array, low, j--);
        sort(array, j++, high);
    }
}