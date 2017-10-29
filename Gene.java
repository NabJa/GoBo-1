package gobi;

import augmentedTree.IntervalTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class Gene {

	public String geneID;
	public int start;
	public int end;
	public String strand;
	public String source;
	public String type;
	public Map<String, RegionVector> transcripts = new HashMap<String, RegionVector>();

	public Collection<String> wtStarts = new ArrayList<String>();
	public Set<Integer> wtEnds = new HashSet<Integer>();
	public Set<Integer> wts = new HashSet<Integer>();

	public void setGene(String id, int start, int end, String strand, String source, String type) {
		this.geneID = id;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.source = source;
		this.type = type;
		// this.transcripts = new HashMap<RegionVector, String>();
	}

	/**
	 * inserts RegionVector in HashMap transcripts
	 * 
	 * @param rv
	 */
	public void insertRV(RegionVector rv) {
		transcripts.put(rv.getID(), rv);
	}

	public String getID() {
		return geneID;
	}

	public void getStarts2() { // Collection<Integer>

		transcripts.forEach((k, v) -> {
			System.out.println(v.getX1() + " " + v.getX2() + "\t" + "\t" + k);
		});
		// transcripts.forEach((k,v) -> {
		// transcripts.forEach((k1,v1) -> {
		// if(k.inverse().getX1() == k1.inverse().getX1()) {
		// wtStarts.add(v1);
		// }
		// });
		// });

		// transcripts.forEach((k,v)->{
		// Collection<Integer> sameStarts = new TreeSet();
		// IntervalTree iTree = new IntervalTree();
		// wtStarts = iTree.getIntervalsIntersecting(k.getX1(), k.getX2(),sameStarts);
		// IntervalTree<Region> it = new IntervalTree<Region>();
		// IntervalTree.getIntervals(k.getX1(), k.getX2(), sameStarts, -1, 3, -1, 3);
		// sameStarts = IntervalTree.getIntervalsEqual(k.getX1(), k.getX2(),
		// sameStarts);
		//
		// System.out.println("ID: " + v + "Region: " + k);
		// });

	}

//	public void getStarts() {
//
//		RVcomperator comp = new RVcomperator();
//		
//		transcripts.forEach((k, v) -> {
//			transcripts.forEach((k1, v1) -> {
//				Region intron = k.inverse().regions.get(0);
//
//				if (comp.containsSameStart(intron, k1)) {
//					wtStarts.add(k1.id);
//				}
//			});
//		});
//
//	}

	public void printWtStarts() {
		for (String e : wtStarts) {
			System.out.println(e);
		}
	}

	public void printTranscriptsInverse() {
		transcripts.forEach((k, v) -> { v.inverse().printRegions(); });

	}
	
	/**
	 * Must be able to find intron with skipped exon.
	 * 
	 * @return
	 */
	// public Region findSkippedExons() {
	// }

}