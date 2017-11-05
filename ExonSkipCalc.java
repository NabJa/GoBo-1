package gobi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ExonSkipCalc {

	public void printRV(RegionVector rv) {
		for (Region r : rv.regions) {
			System.out.println(r.getX1() + " " + r.getX2() + " ");
		}
	}

	public HashSet<String> getSplicedVariants(int start, int end, HashMap<String, RegionVector> transcripts) {

		HashSet<String> splicedVariants = new HashSet<String>();

		transcripts.keySet().forEach((id) -> {
			RegionVector rv = transcripts.get(id);

			for (Region intron : rv.inverse().regions) {
				if (intron.getX1() == start && intron.getX2() == end) {
					splicedVariants.add(rv.id);
				}
			}
		});

		return splicedVariants;
	}

	public HashSet<String> getWTS(int start, int end, HashMap<String, RegionVector> transcripts) {

		HashSet<String> WTS = new HashSet<String>();

		transcripts.keySet().forEach((id) -> {
			RegionVector rv = transcripts.get(id);

			for (Region intron : rv.inverse().regions) {
				int sameStart = 0;
				int sameEnds = 0;
				
				if (intron.getX1() == start) {
					sameStart = 1;
				} else if (intron.getX2() == end) {
					sameEnds = 1;
				} else if ((sameStart + sameEnds) == 2) {
					WTS.add(rv.id);
				}
			}
		});
		return WTS;
	}

	public HashSet<String> hasSkip(HashSet<String> sv, )
	
	/**
	 * Retains 2 sets. You get all elements that are in BOTH sets.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public HashSet<String> retainLists(HashSet<String> setA, HashSet<String> setB) {
		setA.retainAll(setB);
		return setA;
	}

	public RegionVector subtract(Region a, RegionVector b, OutputMap outMap) {
		RegionVector introns = new RegionVector();
		int i = 0;

		System.out.println(b.id);
		printRV(b);

		if (b.regions.size() > 1) {
			if (regionInRV(a, b) == false) {

				while (b.regions.get(i).getX1() != a.getX1() && i < b.regions.size() - 1) {
					i++;
				}
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
		} else {
			if (b.regions.get(0).getX1() + 1 == a.getX1() && b.regions.get(0).getX2() == a.getX2()) {
				int start = b.regions.get(0).getX1();
				int end = b.regions.get(0).getX2();
				Region skippedIntron = new Region(start, end);
				introns.addRegion(skippedIntron);
			}
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

		HashSet<String> splicedV = new HashSet<String>();
		HashSet<String> wildT = new HashSet<String>();
		HashSet<String> sameIntrons = new HashSet<String>();

		splicedV = getSplicedVariants(start, end, transcripts);
		
		wildT = getWTS(start, end, transcripts);

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

			// if (outMap.isNotInResults(intron)) {
			// skippedExons = subtract(intron, queryRV, outMap);
			// }

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

				// output.getAllSVProtIDs(rv);
				// output.getAllSVProtIDs(gene.transcripts.get(id));
				output.setOutput(gene, intron, skippedExons);

				outMap.resultMap.put(intron, output);
				// outMap.resultMap.put(intron, output);
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
