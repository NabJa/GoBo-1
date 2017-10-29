package gobi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

	/**
	 * If position == 0 it returns true if RV contains same start as input intron.
	 * If position != 0 it returns true if RV contains same end as input intron.
	 * 
	 * @param intron
	 * @param rv
	 * @param position
	 * @return
	 */
	public boolean containsSamePosition(Region intron, RegionVector rv, int position) {
		if (position == 0) {
			int intronStart = intron.getX1();
			for (int i = 0; i < rv.regions.size(); i++) {
				Region RVintrons = rv.regions.get(i);
				int RVstarts = RVintrons.getX1();
				if (intronStart == RVstarts) {
					return true;
				}
			}
			return false;
		} else {
			int intronEnd = intron.getX2();
			for (int i = 0; i < rv.regions.size(); i++) {
				Region RVintrons = rv.regions.get(i);
				int RVends = RVintrons.getX2();
				if (intronEnd == RVends) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * It will return all IDs of transcripts with same start as input start
	 * 
	 * @param start
	 * @param transcripts
	 * @return
	 */
	public HashSet<String> getStarts(int start, HashMap<RegionVector, String> transcripts) {

		HashSet<String> sameStarts = new HashSet<String>();

		transcripts.forEach((rv, id) -> {
			for (Region intron : rv.inverse().regions) {
				if (intron.getX1() == start) {
					sameStarts.add(rv.id);
				}
			}
		});

		return sameStarts;
	}

	/**
	 * Returns all IDs of transcripts with same ends as input start
	 * 
	 * @param start
	 * @param transcripts
	 * @return
	 */
	public HashSet<String> getEnds(int end, HashMap<RegionVector, String> transcripts) {

		HashSet<String> sameEnds = new HashSet<String>();

		transcripts.forEach((rv, id) -> {
			for (Region intron : rv.inverse().regions) {
				if (intron.getX2() == end) {
					sameEnds.add(rv.id);
				}
			}
		});
		return sameEnds;
	}

	/**
	 * Retains 2 sets. You get all elements that are in BOTH sets.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public HashSet<String> getSameIntron(HashSet<String> setA, HashSet<String> setB) {
		setA.retainAll(setB);
		return setA;
	}

	/**
	 * Returns Region(0,0) if input RegionVector contains only 1 or 0 regions. Else
	 * it returns the inverse of b...
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public RegionVector subtract(Region a, RegionVector b) {

		RegionVector skippedExons = new RegionVector();

		if (b.regions.size() < 2) {
			Region zero = new Region(0, 0);
			skippedExons.addRegion(zero);
		} else {
			for (int i = 0; i < b.regions.size() - 1; i++) {
				Region exon = new Region(b.regions.get(i).getX2(), b.regions.get(i + 1).getX1());
				skippedExons.addRegion(exon);
			}
		}

		return skippedExons;
	}

	public HashSet<String> getOverlappingIntrons(int start, int end, HashMap<RegionVector, String> transcripts) {

		HashSet<String> sameStarts = new HashSet<String>();
		HashSet<String> sameEnds = new HashSet<String>();
		HashSet<String> sameIntrons = new HashSet<String>();

		sameStarts = getStarts(start, transcripts);
		sameEnds = getEnds(end, transcripts);

		sameIntrons = getSameIntron(sameStarts, sameEnds);

		return sameIntrons;
	}

	public Output getSkippedExons(Region intron, HashMap<RegionVector, String> transcripts) {
		
		HashSet<String> sameIntrons = new HashSet<String>();
		sameIntrons = getOverlappingIntrons(intron.getX1(), intron.getX2(), transcripts);
		
		for(String id : sameIntrons) {
			if(transcripts.containsValue(id)) {
				RegionVector skippedExons = new RegionVector();
				skippedExons = subtract(intron, getKey(transcripts, id));
			} else {
				
			}
		}
	}
	
	public  <K, V> Set<K> getKey(Map<K, V> map, V value) {
	    return map.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), value)).map(Map.Entry::getKey).collect(Collectors.toSet());
	}
}
