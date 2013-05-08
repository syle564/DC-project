package service;

import java.util.Comparator;
import model.LoadingDock;


public class DockIDComparator implements Comparator<LoadingDock> {
			public int compare(LoadingDock o1, LoadingDock o2)
			{
				return o1.getDockID()-o2.getDockID();
			}
}
