package gobi;

import java.util.Comparator;

public class RegionComperator implements Comparator<Region> {

	public int compare(Region r1, Region r2) {
		if(r1.getX1() == r2.getX1() && r1.getX2() == r2.getX2()) {
			return 0;		
		} else if(r1.getX1() < r2.getX1()) {
			return -1;
		} else if(r1.getX1() > r2.getX1()) {
			return 1;
		}
		return 1;
	}
}
