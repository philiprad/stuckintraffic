/*
 * @author  Maxim Vasilishin
 * @version 4.0
 */
package pathFiler;

import java.util.Comparator;
import trafficInfrastructure.roadPath.Path;

// TODO: Auto-generated Javadoc
/**
 * The Class PathComparator.
 */
public class PathComparator implements Comparator<Path>{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Path o1, Path o2) {
		
			int crit1 = o1.getPathPoints().get(0).getX() - o2.getPathPoints().get(0).getX();
			if(crit1 ==0)
				crit1 = o1.getPathPoints().get(0).getX() - o2.getPathPoints().get(0).getX();
			if(crit1 ==0)
				crit1 = o1.getPathPoints().get(o1.getPathPoints().size()-1).getX() - o2.getPathPoints().get(o2.getPathPoints().size()-1).getX();
			if(crit1==0)
				crit1 = o1.getPathPoints().get(o1.getPathPoints().size()-1).getY()- o2.getPathPoints().get(o2.getPathPoints().size()-1).getY();
			if(crit1==0)
				crit1=o1.getPathPoints().size()-o2.getPathPoints().size();

			return crit1;
	}
}
