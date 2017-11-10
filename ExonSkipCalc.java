package gobi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ExonSkipCalc {

	public boolean regionInRV(Region r, RegionVector rv) {
		boolean bol = false;
		for (Region a : rv.regions) {
			if (a.getX1() == r.getX1() && a.getX2() == r.getX2()) {
				bol = true;
			}
		}
		return bol;
	}

	public HashSet<String> getOverlappingIntrons(int start, int end, HashMap<String, RegionVector> transcripts) {

		HashSet<String> sameStarts = new HashSet<String>();
		HashSet<String> sameEnds = new HashSet<String>();
		HashSet<String> sameIntrons = new HashSet<String>();

		sameStarts = getStarts(start, transcripts);
		sameEnds = getEnds(end, transcripts);

		sameIntrons = getSameIntron(sameStarts, sameEnds);

		return sameIntrons;
	}	
	public HashSet<String> getSameIntron(HashSet<String> setA, HashSet<String> setB) {
		setA.retainAll(setB);
		return setA;
	}
	public HashSet<String> getStarts(int start, HashMap<String, RegionVector> transcripts) {

		HashSet<String> sameStarts = new HashSet<String>();

		transcripts.keySet().forEach((id) -> {
			RegionVector rv = transcripts.get(id);
			// System.out.println("RegionVecot");
			// printRV(rv);
			for (Region intron : rv.inverse().regions) {
				// System.out.println(rv.id);
				// System.out.println("intron: " + intron.getX1() + " " + intron.getX2());
				// printRV(rv.inverse());

				if (intron.getX1() == start) {
					sameStarts.add(rv.id);
				}
			}
		});

		return sameStarts;
	}
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

	
	public HashSet<String> getSVs(Region intron, HashMap<String, RegionVector> transcripts, Output out) {

		HashSet<String> svs = new HashSet<String>();

		transcripts.keySet().forEach((id) -> { // For every transcript ID

			RegionVector rv = transcripts.get(id); // Get transcript values

			for (Region r : rv.inverse().regions) // For every Intron in transcript:
			{
				if (r.getX1() == intron.getX1() && r.getX2() == intron.getX2()) // If intron == intron of any other
																				// transcript
				{
					svs.add(rv.id);
				}
			}
		});
		out.sv_prots = svs;
		return svs;
	}

	public HashSet<String> getWTs(Region intron, HashMap<String, RegionVector> transcripts, Output out) {

		HashSet<String> wts = new HashSet<String>();
		Set<String> skippedExons = new HashSet<String>();

		for (String id : transcripts.keySet()) // For every transcript ID
		{
			RegionVector rv = transcripts.get(id); // Get transcript values

			for (Region r : rv.regions) // For every Region of transcript
			{

				// System.out.println("intron " + intron.getX1() + " " + intron.getX2());
				// System.out.println("region " + r.getX1() + " " + r.getX2());

				if (intron.getX1() < r.getX1() && intron.getX2() > r.getX2()) // if region is in intron:
				{
					// System.out.println(rv.id);

					wts.add(rv.id);

					skippedExons.add(r.regionID);

				}
			}
		}

		out.wt_prots.addAll(skippedExons);
		return wts;
	}

	public RegionVector getIntrons(Region intron, HashMap<String, RegionVector> transcripts, Output out) {

		RegionVector skippedIntrons = new RegionVector();

		HashSet<String> wildtypes = getWTs(intron, transcripts, out); // IDs of transcripts with exon skipping
		HashSet<String> splicedvar = getSVs(intron, transcripts, out); // Ids of transcripts with same spliced Variant

		// System.out.println(wildtypes.size());
		// System.out.println(splicedvar.size());
		// System.out.println();

		// if(wildtypes.size() ==0) {
		// }else {
		// System.out.println("t");
		// }

		if (wildtypes.size() > 0) // wenn es exon skipping gibt!!
		{
			// System.out.println(wildtypes.size());

			// For every transcript ID with exon skipping
			for (String id : wildtypes) {
				RegionVector queryRV = transcripts.get(id); // Transcript values
				
//				queryRV.printRegions();
				
				queryRV = queryRV.inverse(); // Transcripts Introns
				
//				queryRV.printRegions();
//				System.out.println();

				if(queryRV.id.equals("ENSMUST00000141711")) {
					System.out.println("stop");
				}
				
				//Intron != a intron of queryRV
				if (regionInRV(intron, queryRV) == false && queryRV.getSize() > 1) {

					int i = 0;

					// while start of intron != start of intron of transcript continue
					while (queryRV.regions.get(i).getX1() != intron.getX1() && i < queryRV.regions.size() - 1) {
						i++;
					}

					// while end of intron != end of intron of transcript save intron in
					// skippedIntrons
					while (queryRV.regions.get(i).getX2() != intron.getX2()) {
						int start = queryRV.regions.get(i).getX1();
						int end = queryRV.regions.get(i).getX2();
						Region skippedIntron = new Region(start, end);
						skippedIntrons.addRegion(skippedIntron);
						i++;
					}
					int start = queryRV.regions.get(i).getX1();
					int end = queryRV.regions.get(i).getX2();
					Region skippedIntron = new Region(start, end);
					skippedIntrons.addRegion(skippedIntron);
				}
			}
		}
		return skippedIntrons;

	}

	public void getAll(Gene gene, Region intron, HashMap<String, RegionVector> transcripts, OutputMap outMap) {

		Output out = new Output();

		RegionVector skippedIntrons = getIntrons(intron, transcripts, out);
		if (skippedIntrons.getSize() == 0) {
			return;
		}
		out.maxSkippedBases = 1;
		out.minSkippedBases = 1;
		out.maxSkippedExons = 1;
		out.minSkippedExons = 1;

		out.setOutput(gene, intron, skippedIntrons);

		outMap.resultMap.put(intron, out);
	}

	public void getSkippedExonFromGen(Gene gene, OutputMap outMap) {

		for (RegionVector rv : gene.transcripts.values()) // For every transcript in a gene:
		{
			for (Region r : rv.inverse().regions) // For every intron in a transcript:
			{
				getAll(gene, r, gene.transcripts, outMap);
			}
		}
	}

}
