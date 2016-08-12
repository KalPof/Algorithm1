public class ins_sort
{
    public ins_sort()
    {
        
    }    
    
    public static void sort(Comparable[] array)
    {
        int i=0;
        
        while(i < array.length)
        {
            int n=0;
            
            while(n <= i)
            {
                //System.out.println("just the print of the elements: " + array[i] + "-" + array[n]);
                System.out.println(array[n]);
                if(less(array[i], array[n]))
                {
                    exchange(array, i, n);
                }
                
                n++;
            }
            
            System.out.println("");
            i++;
        }
    }
    
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
    
    
    public static void main(String args[])
    {
        ins_sort j = new ins_sort();
        Integer[] intar = {1,4,3,5,8,6,9,4,2};
        
        j.sort(intar);
    }
}