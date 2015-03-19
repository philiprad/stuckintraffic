package pathFiler;

public class PathComparator implements Comparator<Path>{
	
	public int compare(Appointment a, Appointment b)
	{
	    if (a.getDay() < b.getDay())
	        return -1;

	    if (a.getDay() == b.getDay())
	    {
	        if (a.getStart() < b.getStart())
	            return -1;
	        if (a.getStart() > b.getStart())
	            return 1;
	        return 0;
	    }

	    return 1;
	}
}
