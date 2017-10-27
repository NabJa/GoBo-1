package gobi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class RegionVector {

	public String id; // transcript ID
	public int x1; // transcript start
	public int x2; // transcript end

	public ArrayList<Region> regions = new ArrayList<Region>(); // maybe Vector instead of ArrayList???

	public Set<Integer> wtstarts = new HashSet<Integer>();
	public Set<Integer> wtends = new HashSet<Integer>();
	public Set<Integer> wts = new HashSet<Integer>();

	public RegionVector() {
	} // empty constructor to be able to create without variables or with

	public RegionVector(String id, int x1, int x2) {
		this.id = id;
		this.x1 = x1;
		this.x2 = x2;
	}

	public String getID() {
		return id;
	}

	public int getX1() {
		return x1;
	}

	public int getX2() {
		return x2;
	}

	public int getLength() {
		return x2 - x1;
	}

	public void addRegion(Region region) {
		regions.add(region);
	}

	public int[] getTransLoc() {
		int[] region = { x1, x2 };
		return region;
	}

	/**
	 * Returns an ArrayList<Region> that are the inverse of given RegionVector.
	 * Example: Input{1,2; 3,4; 5,6} -> Output{2,3; 4,5}
	 * @return
	 */
	public ArrayList<Region> arrayInverse() {

		ArrayList<Region> introns = new ArrayList<Region>();

		for (int i = 0; i < regions.size() - 1; i++) {
			Region intron = new Region(regions.get(i).getX2(), regions.get(i + 1).getX1());
			introns.add(intron);
		}

		return introns;
	}

	/**
	 * Returns an RegionVector that are the inverse of given RegionVector.
	 * Example: Input{1,2; 3,4; 5,6} -> Output{2,3; 4,5}
	 * @return
	 */
	public RegionVector inverse() {

		RegionVector introns = new RegionVector();

		for (int i = 0; i < regions.size() - 1; i++) {
			Region intron = new Region(regions.get(i).getX2(), regions.get(i + 1).getX1());
			introns.addRegion(intron);
		}

		return introns;
	}

	public int hashCode() {
		return ((x1 * 104723) % 104729) + ((x2 * 104717) % 104711);
	}

}