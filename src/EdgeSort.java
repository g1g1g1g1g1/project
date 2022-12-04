import java.util.Comparator;

public class EdgeSort implements Comparator<Edge> 
{
    public int compare(Edge a, Edge b) 
    {
	    int diff = a.w - b.w;
	    
        if(diff == 0)
        {
            diff = a.u.compareTo(b.u);
        }
	    
        if(diff == 0) 
        {
            diff = a.v.compareTo(b.v);
        }
	    
        return diff;
    }

    public int compare2(Edge a, Edge b)
    {
        int diff = a.getN() - b.getN();

        if(diff == 0)
        {
            diff = a.u.compareTo(b.u);
        }

        if(diff == 0)
        {
            diff = a.v.compareTo(b.v);
        }

        return diff;
    }
}

