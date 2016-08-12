public class bubblesort
{
    public bubblesort()
    {}
    
    public void sort(Comparable[] array)
    {
        int i=0;
        int counter=0;
        
        while(counter < array.length-1)
        {
            counter =0;
            
            for(int j=1; j < array.length; j++)
            {
                //System.out.println(array[i] + "-" + array[j]);
                if(!less(array[i], array[j]))
                {
                    exchange(array, j,i);
                }else{
                    counter++;
                }
                
                i++;
            }
            
            i=0;
        }
        
        printer(array);
        
    }
    
    public void printer(Comparable[] t)
    {
        for(int h=0; h < t.length; h++)
        {
            System.out.println(t[h]);
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
        bubblesort j = new bubblesort();
        Integer[] intar = {1,4,3,5,8,6,9,4,2,3};
        j.sort(intar);
    }
}