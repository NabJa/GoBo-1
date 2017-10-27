package gobi;

import java.util.Set;
import java.util.TreeSet;

public class RVcomperator {

	/**
	 * Takes 2 RegionVectors and returns a set with all overlapping starts
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public Set<Integer> compareStarts(RegionVector o1, RegionVector o2) {
		Set<Integer> sameStarts = new TreeSet<Integer>();
		for (int i = 0; i < o1.regions.size(); i++) {
			if (o1.inverse().regions.get(i).getX1() == o2.inverse().regions.get(i).getX1()) {
				sameStarts.add(i);
			}
		}
		return sameStarts;
	}


	/**
	 * Takes 2 RegionVectors and returns a set with all overlapping ends
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public Set<Integer> compareEnds(RegionVector o1, RegionVector o2) {
		Set<Integer> sameEnds = new TreeSet<Integer>();
		for (int i = 0; i < o1.regions.size(); i++) {
			if (o1.inverse().regions.get(i).getX2() == o2.inverse().regions.get(i).getX2()) {
				sameEnds.add(i);
			}
		}
		return sameEnds;
	}
}
