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
	public Map<RegionVector, String> transcripts = new HashMap<RegionVector, String>();
 
	public Collection<Integer> wtStarts = new ArrayList<Integer>();
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
		transcripts.put(rv, rv.getID());
	}

	public String getID() {
		return geneID;
	}

	public void getStarts() { //Collection<Integer>
		transcripts.forEach((k,v)->{	

			Collection<Integer> sameStarts = new TreeSet();
			IntervalTree iTree = new IntervalTree();
	
			wtStarts = iTree.getIntervalsIntersecting(k.getX1(), k.getX2(),sameStarts);
			
//			IntervalTree<Region> it = new IntervalTree<Region>();	
//			IntervalTree.getIntervals(k.getX1(), k.getX2(), sameStarts, -1, 3, -1, 3);			
//			sameStarts = IntervalTree.getIntervalsEqual(k.getX1(), k.getX2(), sameStarts);
			
			//System.out.println("ID: " + v + "Region: " + k);
		});

	}
	
	public void printWtStarts() {
		for(int e : wtStarts) {
			System.out.println(e);
		}
	}

	/**
	 * Must be able to find intron with skipped exon.
	 * 
	 * @return
	 */
	// public Region findSkippedExons() {
	// }

}