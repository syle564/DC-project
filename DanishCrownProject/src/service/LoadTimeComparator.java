package service;
import java.util.Comparator;

import model.Load;
public class LoadTimeComparator implements Comparator<Load>
{

	
		public int compare(Load o1, Load o2)
		{

			return o1.getEstStartTime().compareTo( o2.getEstStartTime());
		}
		
}


