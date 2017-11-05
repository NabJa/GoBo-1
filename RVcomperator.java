package gobi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class RVcomperator {

	public void printRV(RegionVector rv) {
		for (Region r : rv.regions) {
			System.out.println(r.getX1() + " " + r.getX2() + " ");
		}
	}

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
			if (o1.regions.get(i).getX1() == o2.regions.get(i).getX1()) { // o1.inverse().regions.get(i).getX1() ==
																			// o2.inverse().regions.get(i).getX1()
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
			if (o1.regions.get(i).getX2() == o2.regions.get(i).getX2()) { // o1.inverse().regions.get(i).getX2() ==
																			// o2.inverse().regions.get(i).getX2())
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
	public HashSet<String> getStarts(int start, HashMap<String, RegionVector> transcripts) {

		HashSet<String> sameStarts = new HashSet<String>();

		transcripts.keySet().forEach((id) -> {
			RegionVector rv = transcripts.get(id);
//			System.out.println("RegionVecot");
//			printRV(rv);
			for (Region intron : rv.inverse().regions) {
//				 System.out.println(rv.id);
//				 System.out.println("intron: " + intron.getX1() + " " + intron.getX2());
//				 printRV(rv.inverse());

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
	public HashSet<String> getEnds(int end, HashMap<String, RegionVector> transcripts) {

		HashSet<String> sameEnds = new HashSet<String>();

		transcripts.keySet().forEach((id) -> {
			RegionVector rv = transcripts.get(id);
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

	public RegionVector subtract(Region a, RegionVector b, OutputMap outMap) {
		RegionVector introns = new RegionVector();
		int i = 0;

		while (b.regions.get(i).getX1() != a.getX1() && i < b.regions.size() - 1) {
			i++;
		}

			if (regionInRV(a, b) == false) {
				while (b.regions.get(i).getX2() != a.getX2()) {
					int start = b.regions.get(i).getX1();
					int end = b.regions.get(i).getX2();
					Region skippedIntron = new Region(start, end);
					introns.addRegion(skippedIntron);
					i++;
				}
				int start = b.regions.get(i).getX1();
				int end = b.regions.get(i).getX2();
				Region skippedIntron = new Region(start, end);
				introns.addRegion(skippedIntron);
			}
		
		return introns;
	}

	public boolean regionInRV(Region r, RegionVector rv) {
		boolean bol = false;
		for (Region a : rv.regions) {
			if (a.getX1() == r.getX1() && a.getX2() == r.getX2()) {
				bol = true;
			}
		}
		return bol;
	}

	/**
	 * Returns a HashSet with strings of the ids of RVs with same start AND same end
	 * in any intron combination
	 * 
	 * @param start
	 * @param end
	 * @param transcripts
	 * @return
	 */
	public HashSet<String> getOverlappingIntrons(int start, int end, HashMap<String, RegionVector> transcripts) {

		HashSet<String> sameStarts = new HashSet<String>();
		HashSet<String> sameEnds = new HashSet<String>();
		HashSet<String> sameIntrons = new HashSet<String>();

		sameStarts = getStarts(start, transcripts);
		sameEnds = getEnds(end, transcripts);

		sameIntrons = getSameIntron(sameStarts, sameEnds);

		return sameIntrons;
	}

	/**
	 * 
	 * @param intron
	 * @param gene
	 * @param transID
	 * @return
	 */
	public void getSkippedExon(Region intron, Gene gene, String transID, OutputMap outMap, RegionVector rv) {

		HashSet<String> sameIntrons = new HashSet<String>();

		sameIntrons = getOverlappingIntrons(intron.getX1(), intron.getX2(), gene.transcripts);
		sameIntrons.remove(transID);

		for (String id : sameIntrons) {

			RegionVector skippedExons = new RegionVector();
			RegionVector queryRV = gene.transcripts.get(id);

			queryRV = queryRV.inverse();

//			if (outMap.isNotInResults(intron)) {
//			skippedExons = subtract(intron, queryRV, outMap);
//			}

			if (!outMap.isInResultMap(intron)) {
			skippedExons = subtract(intron, queryRV, outMap);
			}
			
			if (skippedExons.regions.size() > 0) {
				Output output = new Output();
				
				int skippedBases = skippedExons.inverse().getRegionLength();
				output.insertMinSkippedBases(skippedBases);
				output.insertMaxSkippedBases(skippedBases);
				
				output.getAllSVProtIDs(rv);
				output.getAllWTProtIDs(gene.transcripts.get(id));
				
//				output.getAllSVProtIDs(rv);
//				output.getAllSVProtIDs(gene.transcripts.get(id));
				output.setOutput(gene, intron, skippedExons);

				outMap.resultMap.put(intron ,output);
//				outMap.resultMap.put(intron, output);
			}
		}
	}

	public void getSkippedExonFromGen(Gene gene, OutputMap outMap) {
		for (RegionVector rv : gene.transcripts.values()) {
			for (Region r : rv.inverse().regions) {
				getSkippedExon(r, gene, rv.id, outMap, rv);
			}
		}
	}

}
