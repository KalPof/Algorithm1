//i use this file to build the function used on the sorting methods

//CompareTo and Comparable: this is just an example of a class which implements the comparable interface

public class Date implements Comparable<Date>
{
    public int year=0, month=0, day=0;
    
    //add the methods for date (es constructor or setter)
    
    public int CompareTo(Date d)
    {
        if(d.year > this.year){return 1;}
        if(d.year < this.year){return -1;}
        if(d.month > this.month){return 1;}
        if(d.month < this.month){return -1;}
        if(d.day > this.day){return 1;}
        if(d.day < this.day){return -1}
        return 0;
    }
}

//other usefull methods used in combination with comparable and the sorting methods

public static boolean less(Comparable a, Comparable b)
{
    if(a.compareTo(b) == 1){ 
        return false; 
    }else{
        return true;
    }
    
}

public static void exchange(Comparable[] a, int i, int j)
{
    Comparable change = a[i];
    i=j;
    a[j] = change;
}
