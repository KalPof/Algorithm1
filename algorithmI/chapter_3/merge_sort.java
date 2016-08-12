public class merge_sort
{
    
    /* the merge sort is a sorting method based on the divide-and-conquest model. As said, we divide the array into portions smaller and 
    smaller, creating a more easy problem as the solutions became smaller. This procedure is a recursion based method.
    The logic of mergesort is in fact find a middle point a partially sorted array (a copy, usually) and, based on that point, find the elements of the array
    to put first or last of the current element in the "real" array. More informations here https://it.wikipedia.org/wiki/Merge_sort 
    and here http://algs4.cs.princeton.edu/22mergesort/
    */
    
    Comparable[] copy;
    
    public merge_sort(){}
    
    public boolean isSorted(Comparable[] a,)
    {
        
        //just a check if the current array is sorted or not, u can do it!
        
        for(int j=1; j<a.length; j++)
        {
            if(!less(a[j--], a[j]){ return false;}
        }
        return true;
    }
    
    public static boolean less(Comparable a, Comparable b)
    {
        if(a.compareTo(b) == 1){ 
            return false;
        }else{
            return true;
        }
    }
    
    //the method below is used for the creation of a recursive mergesort. Mergesort is one of the best alogrithm used for the sorting of comparable elements
    
    private void merge(Comparable[] a, Comparable[] a_copy, int low, int mid, int high)
    {
        
        // the variables are: a = the array we need to sort, a_copy = the copy of the array a, low=the pointer of the left part of the copied array, mid= the pointer
        //of the element in the middle position of a_copy, high= the last element of the array (and clearly also the last element of the right portion of the copied array)
        
        assert isSorted(a, low, mid);
        assert isSorted(a, mid+1, high); 
        //these 2 methods are used for check if the entire array to sort is already partially sorted (from low to mid, at least)
        
        for(int i=low; i <=high; i++)
        {
            a[i] = a_copy[i];
        }
        //the for cycle above is used to create a copy of the array that we need to sort. The copy is essential for the splitting of the array and the replace 
        //of the elements in the original array (in fact, we look at the copied array for the replacement)
        
        int j=low, l=mid+1; //we instantiate those variables for the replacements of elements from the copied array to the real array
        
        int(i=low; i<= high; i++)
        {
            if(j > mid) a[i] = a_copy[l]; l++;
            else if(l > high) a[i] = a_copy[j]; j++; 
            //the 2 istructions written aside from here are used if the copied array has already reached one of the 2 limits (mid and high)
            //so  now we must move the other part of the copied array into the real one
            else if(less(a[j], a[l])) a[i] = a_copy[j]; j++;
            else a[i] = a_copy[l]; l++;
        }
        
        assert isSorted(a,low,high); //just a check foor ensuring that the array was sorted right
    }
    
    private static void Sort(Comparable[] a, Comparable[] a_copy, int low, int high)
    {
        //this method is the real part of the sort method. It combines the part of recursion of sort with merge. More explanations ahead
        
        if( high <= low) return; //this method checks if the high pointer is less or equal low. If it's, the are (at least logically) facing an empty array,
        //so there's no need of sorting
        
        int mid = low + ((high-low)/2); //we set the midpoint based on the positions low and high passed into the method 
        //(we just need a recall of this private into a public one for it to works)
        
        Sort(a, a_copy, low, mid); 
        Sort(a, a_copy, mid+1, high);
        
        //the 2 methods above just Sort the copied array. One sorts from low to mid (which is from the position middle to 0, logically)
        // and the other one sorts from the elements next the middle to the end, leading to "the formation" of 2 sorted subarrays
        
        merge(a, a_copy, low, mid, high); //the real key of the programm is the merge call at the bottom, which recursively sorts 
        //the portions of the array and then moves and merge them together into the real array
    }
    
    public static void sort(Comparable[] a)
    {
        //the public method that we need fot the call of the private method sort
        
        copy = new Comparable[a.length];
        Sort(a,copy,0,a.length);
    }
}