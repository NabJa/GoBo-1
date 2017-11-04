package gobi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Gene {

	public String geneID;
	public int start;
	public int end;
	public char strand;
	public String source;
	public String type;
	public String geneChr;
	public String geneName;

	public HashMap<String, RegionVector> transcripts = new HashMap<String, RegionVector>();

	public Collection<String> wtStarts = new ArrayList<String>();
	public Set<Integer> wtEnds = new HashSet<Integer>();
	public Set<Integer> wts = new HashSet<Integer>();

	public void setGene(String id, int start, int end, char strand, String geneChr, String source, String type,
			String geneName) {
		this.geneID = id;
		this.start = start;
		this.end = end;
		this.strand = strand;
		this.source = source;
		this.type = type;
		this.geneChr = geneChr;
		this.geneName = geneName;
		this.transcripts = new  HashMap<String, RegionVector>();
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

	public int nTrans() {
		int i = 0;
		for (RegionVector r : transcripts.values()) {
			i += r.getSize();
		}
		return i;
	}

	public void getStarts2() { // Collection<Integer>

		transcripts.forEach((k, v) -> {
			System.out.println(v.getX1() + " " + v.getX2() + "\t" + "\t" + k);
		});
	}

	public void printWtStarts() {
		for (String e : wtStarts) {
			System.out.println(e);
		}
	}

	public void printTranscriptsInverse() {
		transcripts.forEach((k, v) -> {
			v.inverse().printRegions();
		});

	}


}