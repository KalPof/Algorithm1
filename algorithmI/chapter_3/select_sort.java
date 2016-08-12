public class select_sort
{
    public select_sort(){}
    
    public void sort(Comparable[] a)
    {
        int i=0;
        while(i < a.length)
        {
            int min = i;
            for(int n=i+1; n < a.length; n++)
            {
                //System.out.println(a[i] + " " + a[n]);
                if(less(a[n], a[min])== true)
                {
                    min = n;
                }
                
            }
            exchange(a, i, min);
            System.out.println(a[i]);
            i++;
        }
    }
    
    public void exchange(Comparable[] a, int i, int j)
    {
        Comparable changer = a[i];
        a[i] = a[j];
        a[j] = changer;
    }
    public boolean less(Comparable a, Comparable b)
    {
        if(a.compareTo(b)== 1){ 
            return false;
        }else{
            return true;
        }
    } 
    
    public static void main(String[] args) {
        select_sort k = new select_sort();
        Integer[] a = {1,4,7,3,2,7,4,0,7,3,9,8};
        k.sort(a);
        //System.out.println(a);
    }

}
