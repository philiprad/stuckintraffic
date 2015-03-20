package pathFiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

import trafficInfrastructure.roadPath.Path;

public class PathComparator implements Comparator<Path>{

	@Override
	public int compare(Path o1, Path o2) {
		
		//if(o1.getPathPoints().get(0).getX()==o2.getPathPoints().get(0). 
		//	o1.getPathPoints().get(0).getY()==o2.getPathPoints().get(0).getY())// &&
			//o1.getPathPoints().get(o1.getPathPoints().size()-1);
//		ArrayList<Path> pp = new ArrayList<Path>();
//		pp.add(o1);
//		pp.add(o2);
//		Collections.sort(pp);
			int crit1 = o1.getPathPoints().get(0).getX() - o2.getPathPoints().get(0).getX();
			if(crit1 ==0)
				crit1 = o1.getPathPoints().get(0).getX() - o2.getPathPoints().get(0).getX();
			if(crit1 ==0)
				crit1 = o1.getPathPoints().get(o1.getPathPoints().size()-1).getX() - o2.getPathPoints().get(o2.getPathPoints().size()-1).getX();
			if(crit1==0)
				crit1 = o1.getPathPoints().get(o1.getPathPoints().size()-1).getY()- o2.getPathPoints().get(o2.getPathPoints().size()-1).getY();
			if(crit1==0)
				crit1=o1.getPathPoints().size()-o2.getPathPoints().size();
			//if(crit1 ==0)
				//crit1=  o1.getPathPoints().get(o1.getPathPoints().size()-1).getY()- o2.getPathPoints().get(o2.getPathPoints().size()-1).getY();

			return crit1;
			
				
			//	o1.getPathPoints().get(o1.getPathPoints().size()-1).getY()==o2.getPathPoints().get(o2.getPathPoints().size()-1).getY()){
//				return 0;
//			}else{
//				return 1;
//			}
					//(o2.getPathPoints().get(o2.getPathPoints().size()-1));
//			
//			boolean comparare2 = o1.getPathPoints().get(0).equals(o2.getPathPoints().get(0));
//			if(comparare & comparare2)
//				return 0;
//			else
//				return 1;
			//return ;
			//o2.getPathPoints().get(o2.getPathPoints().size()-1).getX() //&& 
			//o1.getPathPoints().get(o1.getPathPoints().size()-1).getY()==o2.getPathPoints().get(o2.getPathPoints().size()-1).getY()?0:-1;
		
//			return (this.arrPathPoint.get(this.arrPathPoint.size()-1).getX()==((Path)o).getPathPoints().get(((Path)o).getPathPoints().size()-1).getX()  &&
//					this.arrPathPoint.get(this.arrPathPoint.size()-1).getY()==((Path)o).getPathPoints().get(((Path)o).getPathPoints().size()-1).getY() &&
//							this.arrPathPoint.get(0).getX()==((Path)o).getPathPoints().get(0).getX() &&
//							this.arrPathPoint.get(0).getY()==((Path)o).getPathPoints().get(0).getY())?0:1;
			
			
		//	return o1.getPathPoints().size() - o2.getPathPoints().size();
//		}else{
//			return 0;
//		}
		
		
		// TODO Auto-generated method stub
	}
//	public int compare(Path a, Path b)
//	{
//	    if (a.() < b.getDay())
//	        return -1;
//
//	    if (a.getDay() == b.getDay())
//	    {
//	        if (a.getStart() < b.getStart())
//	            return -1;
//	        if (a.getStart() > b.getStart())
//	            return 1;
//	        return 0;
//	    }
//
//	    return 1;
//	}

	

	
}
