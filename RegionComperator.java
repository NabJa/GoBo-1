package gobi;

import java.util.Comparator;

public class RegionComperator implements Comparator<Region> {

	public int compare(Region r1, Region r2) {
		return r1.getX1() - r2.getX1() + r1.getX2() - r2.getX2();
	}
}
