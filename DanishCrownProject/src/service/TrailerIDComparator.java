package service;

import java.util.Comparator;


import model.Trailer;


	public class TrailerIDComparator implements Comparator<Trailer>
	{

		
			public int compare(Trailer o1, Trailer o2)
			{

				return o1.getTrailerID().compareTo( o2.getTrailerID());
			}
}
